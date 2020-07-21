package com.shiro.service;

import com.shiro.entity.Menu;

import java.util.List;

/**
 * 获取菜单
 */
public interface MenuService {
    //根据用户id获取菜单
     List<Menu> findMenu(String userId);
}
