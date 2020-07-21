package com.shiro.netty;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

public class SecureChatClient {
	
	public void start(String host,int port) throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
		    b.group(group).channel(NioSocketChannel.class)
			    .handler(new ChannelInitializer<SocketChannel>(){
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						String cChatPath =  System.getProperty("user.dir")+"/src/main/resources/conf/cChat.jks";
						SSLEngine engine = SecureChatSslContextFactory.getClientContext(cChatPath,cChatPath).createSSLEngine();
						engine.setUseClientMode(true);//客户方模式
						pipeline.addLast("ssl", new SslHandler(engine));
						pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
						pipeline.addLast("decoder", new StringDecoder());
						pipeline.addLast("encoder", new StringEncoder());
						pipeline.addLast("handler", new SecureChatClientHandler());
					}
				});
		    Channel ch = b.connect(host, port).sync().channel();
		    ChannelFuture lastWriteFuture = null;
		    BufferedReader in = new BufferedReader(new InputStreamReader(
			    System.in));
		    for (;;) {
				String line = in.readLine();
				if (line == null) {
				    break;
				}
				lastWriteFuture = ch.writeAndFlush(line + "\r\n");
				if ("bye".equals(line.toLowerCase())) {
				    ch.closeFuture().sync();
				    break;
				}
		    }
		    if (lastWriteFuture != null) {
		    	lastWriteFuture.sync();
		    }
		}finally{
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		new SecureChatClient().start("localhost", 8765);

	}

}
