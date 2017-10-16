package com.springbootweb.modules.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootweb.modules.system.mapper.PermissionMapper;
import com.springbootweb.modules.system.pojo.Permission;
import com.springbootweb.modules.system.service.IPermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public List<Permission> listByUsername(String username) {
		return permissionMapper.listByUsername(username);
	}

	@Override
	public List<String> listCodeByUsername(String username) {
		List<String> permissionCodes = new ArrayList<>();
		List<Permission> permissions = listByUsername(username);
		for (Permission permission : permissions) {
			permissionCodes.add(permission.getCode());
		}
		return permissionCodes;
	}

}
