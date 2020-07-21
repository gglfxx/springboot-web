package com.shiro.interceptor;

import com.alibaba.fastjson.JSON;
import com.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录拦截
 */
@Component
public class LoginHandlerInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String,Object> pMap = null;
        //String token = request.getHeader("token");
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        if(null==user){
        	 if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"))) {
                 pMap = new HashMap<>();
                 pMap.put("code", 1);
                 pMap.put("msg", "token过期或不存在");
                 response.setCharacterEncoding("UTF-8");
                 response.getWriter().write(JSON.toJSONString(pMap));
             }
             response.sendRedirect(request.getContextPath()+"/");
             return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
