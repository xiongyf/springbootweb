package com.springbootweb.modules.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootweb.modules.system.mapper.RoleMapper;
import com.springbootweb.modules.system.pojo.Role;
import com.springbootweb.modules.system.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Role add(Role role) {
		roleMapper.insert(role);
		return role;
	}

	@Override
	public List<Role> listByUsername(String username) {
		return roleMapper.listByUsername(username);
	}

	@Override
	public List<String> listCodeByUsername(String username) {
		List<String> roleCodes = new ArrayList<>();
		List<Role> roles = listByUsername(username);
		for (Role role : roles) {
			roleCodes.add(role.getCode());
		}
		return roleCodes;
	}

}
