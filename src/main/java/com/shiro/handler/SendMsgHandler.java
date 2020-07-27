package com.shiro.handler;

import javax.annotation.Resource;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiro.entity.User;
import com.shiro.service.UserService;

/**
 * websocket推送数据
 * @author guigl
 *
 */
@Controller
public class SendMsgHandler {
	
	 @Resource
	 private SimpMessagingTemplate simpMessagingTemplate;
	 
	 @Resource
	 private UserService userService;
	
	@RequestMapping("/page")
	public String pushPage(){
		return "/socket";
	}
	
	@Scheduled(fixedRate = 10000)
    @MessageMapping("/change-notice")  // 接收客户端推送信息的地址，配置了头，所以前端发送应该是"/app/change-notice"
    public void greeting(){
    	User user= userService.findByUsername("admin");
        this.simpMessagingTemplate.convertAndSend("/topic/notice", user);    
    }
}
