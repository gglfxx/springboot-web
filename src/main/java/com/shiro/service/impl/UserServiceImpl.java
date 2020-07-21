package com.shiro.service.impl;


import com.shiro.dao.RoleDao;
import com.shiro.dao.UserDao;
import com.shiro.entity.Result;
import com.shiro.entity.User;
import com.shiro.service.RedisService;
import com.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/***
 * 后台用户登陆
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Resource
    private RedisService redisService;
    
    @Resource
    private RoleDao roleDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /***
     * 登陆
     * @param request
     * @param username
     * @param password
     * @return
     */
    @Override
    public Result<User> login(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        Result<User> result = new Result<User>();
        User user = null;
        String msg = null;
        int code = 1;
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 记住密码
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        // 把用户名和密码封装为 UsernamePasswordToken 对象
        try {
            // 执行登录.
            currentUser.login(token);
            // 登录成功...
            code = 0;
            msg="登陆成功";
        } catch (IncorrectCredentialsException e) {
            token.clear();
            msg = "登录密码错误";
        } catch (ExcessiveAttemptsException e) {
            token.clear();
            msg = "登录失败次数过多";
        } catch (LockedAccountException e) {
            token.clear();
            msg = "帐号已被锁定";
        } catch (DisabledAccountException e) {
            token.clear();
            msg = "帐号已被禁用";
        } catch (ExpiredCredentialsException e) {
            token.clear();
            msg = "帐号已过期";
        } catch (UnknownAccountException e) {
            token.clear();
            msg = "帐号不存在";
        } catch (UnauthorizedException e) {
            token.clear();
            msg = "您没有得到相应的授权！";
        } catch (Exception e) {
            token.clear();
            msg = "出错！！！" + e.getMessage();
        }
        if (code == 0) {
            try {
               user = (User) currentUser.getPrincipal();
            } catch (Exception e) {
                //处理系统异
                msg = "系统异常";
                user = null;
                code = 1;
            }

        }
        result.setCode(code);
        result.setMsg(msg);
        result.setData(user);
        return result;
    }

    /**
     * 退出登录
     */
    @Override
    public void logout(HttpServletRequest request,HttpServletResponse response) {
    	//清除限制登陆的session
    	String key = "shiro:cache:shiro_redis_cache:";
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        redisService.del(key+user.getUsername());
        subject.logout();
    }

    /**
     * 根据用户主键获取权限
     */
	@Override
	public List<String> findPermissionsByUserId(int id) {
		return userDao.findPermissionsByUserId(id);
	}
}
