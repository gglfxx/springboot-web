package com.shiro.dao;

import com.shiro.entity.Menu;

import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface MenuDao {
     List<Menu> findParMenu(@Param("userId") String userId);

     List<Menu> findSubMenu(@Param("id") Integer id);
}
