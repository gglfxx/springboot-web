package com.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiro.entity.User;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserDao extends BaseMapper<User>{
    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
     User findByUsername(@Param("username")String username);

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
     User selectByUsername(@Param("username")String username,@Param("password")String password);
     
     /**
      * 查询当前用户角色权限
      * @param id
      * @return
      */
     List<String> findPermissionsByUserId(@Param("id")int id);

}
