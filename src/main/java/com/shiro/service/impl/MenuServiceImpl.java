package com.shiro.service.impl;

import com.shiro.dao.MenuDao;
import com.shiro.entity.Menu;
import com.shiro.service.MenuService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;
    @Override
    public List<Menu> findMenu(String userId) {
        List<Menu> menus = menuDao.findParMenu(userId);
        for(Menu menu:menus){
            buildMenus(menu.getId(),menu);
        }
        return menus;
    }

    /**
     * 获取子菜单
     * @param id
     * @param menu
     * @return
     */
    public Menu buildMenus(Integer id,Menu menu){
        List<Menu> menus = menuDao.findSubMenu(id);
        menu.setChildren(menus);
        for(Menu newMenu:menus){
            return buildMenus(newMenu.getId(),newMenu);
        }
       return menu;
    }
}
