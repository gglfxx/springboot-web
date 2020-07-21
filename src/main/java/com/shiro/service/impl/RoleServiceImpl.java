package com.shiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shiro.dao.RoleDao;
import com.shiro.service.RoleService;

/**
 * 角色
 * @author guigl
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleDao roleDao;

	@Override
	public List<String> findRoleNameByUserId(int id) {
		return roleDao.findRoleNameByUserId(id);
	}

}
