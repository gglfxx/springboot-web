package com.shiro.config;

import com.shiro.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
 
  @Resource
  private LoginHandlerInterceptor loginInterceptor;
 
  // 这个方法是用来配置静态资源的，比如html，js，css，等等
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  
  }
 
  // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //拦截除login.html的接口请求
    InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);
    registration.addPathPatterns("/**")
    	 .excludePathPatterns("/","/user/login","/kickout","/api/**")
    	 .excludePathPatterns("/druid/**")
    	 .excludePathPatterns("/page")
         .excludePathPatterns("/js/**")
         .excludePathPatterns("/css/**")
         .excludePathPatterns("/font/**")
    	 .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
  }
}