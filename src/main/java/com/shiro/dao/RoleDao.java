package com.shiro.dao;

import java.util.List;

/**
 * 角色权限
 * @author guigl
 *
 */
public interface RoleDao {

	List<String> findRoleNameByUserId(int id);

}
