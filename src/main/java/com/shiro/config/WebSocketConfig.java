package com.shiro.config;

import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 开启websocket支持
 * @author guigl
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	// 添加服务端点,接收服务连接
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 添加一个 /socket 的端点 配置允许跨域
		registry.addEndpoint("/socket").addInterceptors(new HandshakeInterceptor() {
			@Override
			public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
					WebSocketHandler wsHandler, Map<String, Object> attributes) {
				response.getHeaders().add("Access-Control-Allow-Origin", "*");
				return true;
			}

			@Override
			public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
					WebSocketHandler wsHandler, Exception exception) {
			}
		}).setAllowedOrigins("*").withSockJS();
	}

	// 定义消息代理（连接请求的规范）
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 客户端订阅地址前缀（服务端发送）
		registry.enableSimpleBroker("/topic");
		// 客户端发布地址前缀（服务端接收）
		registry.setApplicationDestinationPrefixes("/app");
	}
}
