package com.shiro.service;

import java.util.List;

/**
 * 权限
 * @author guigl
 *
 */
public interface RoleService {

	/**
	 * 根据用户ID获取角色
	 * @param id
	 * @return
	 */
	List<String> findRoleNameByUserId(int id);

}
