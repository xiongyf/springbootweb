
package com.springbootweb.modules.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springbootweb.common.utils.SysResponse;
import com.springbootweb.modules.system.pojo.User;
import com.springbootweb.modules.system.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Value("${server.port}")
	private String port;

	@Resource(name = "userService")
	private IUserService userService;

	@PostMapping("/login")
	public Map<String, Object> login(@RequestParam String username, String password) {
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			map.put("state", 0);
			map.put("msg", "Login fail");
			return map;
		}
		map.put("state", 1);
		map.put("msg", "Login seccess");
		return map;
	}

	@GetMapping("/logout")
	public boolean logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout();
		}
		return true;
	}

	@GetMapping("/unAuthenticated")
	public SysResponse unAuthenticated() {
		return new SysResponse(true, "Haven't logined");
	}

	@GetMapping("/getCurrentUser")
	public User getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return null;
		}
		String username = (String) subject.getPrincipal();
		return userService.getUserByName(username);
	}

	@GetMapping("/port")
	public String port() {
		return port;
	}

}
