package com.springbootweb.modules.system.service;

import java.util.List;

import com.springbootweb.modules.system.pojo.Role;

public interface IRoleService {

	Role add(Role role);

	List<Role> listByUsername(String username);

	List<String> listCodeByUsername(String username);
}
