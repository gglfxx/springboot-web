package com.shiro.handler;

import com.shiro.entity.Result;
import com.shiro.entity.User;
import com.shiro.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 后台管理登录接口
 */
@Controller
public class UserHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserHandler.class);
    
    // 进入登录页面
    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @Resource
    private UserService userService;

    @PostMapping("/user/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response){
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
        logger.info("username:"+username+"--------"+"password:"+password);
        Result<User> result = userService.login(request,response,username,password);
        return result.buildResultJson();
    }

    @GetMapping("/user/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        userService.logout(request,response);
        return "redirect:/";
    }


    @RequestMapping(value = "/kickout", method = RequestMethod.GET)
    public String kickOut(Model model) {
        return "kickout";
    }

    /**
     * 主页
     * @return
     */
    @RequestMapping("/index")
    public String welcome(HttpServletRequest request,Model model){
    	Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        model.addAttribute("user",user);
        return "index";
    }
}
