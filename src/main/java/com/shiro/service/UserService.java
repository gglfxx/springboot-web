package com.shiro.service;

import com.shiro.entity.Result;
import com.shiro.entity.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
	/**
	 * 根据用户名查询账号
	 * @param username
	 * @return
	 */
     User findByUsername(String username);
     /***
      * 登陆
      * @param request
      * @param response
      * @param username
      * @param password
      * @return
      */
     Result<User> login(HttpServletRequest request, HttpServletResponse response, String username, String password);
     /**
      * 登出
      * @param request
      * @param response
      */
     void logout(HttpServletRequest request, HttpServletResponse response);
     /**
      * 根据用户主键获取权限
      * @param id
      * @return
      */
	 List<String> findPermissionsByUserId(int id);
}
