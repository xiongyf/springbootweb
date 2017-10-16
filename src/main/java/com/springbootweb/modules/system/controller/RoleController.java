package com.springbootweb.modules.system.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootweb.modules.system.pojo.Role;
import com.springbootweb.modules.system.service.IRoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Resource(name = "")
	private IRoleService roleService;

	@GetMapping("/add")
	public Role add(@Valid Role role) {
		Role role2 = roleService.add(role);
		return role2;

	}

}
