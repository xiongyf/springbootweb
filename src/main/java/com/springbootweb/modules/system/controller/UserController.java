
package com.springbootweb.modules.system.controller;

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
    public SysResponse login(@RequestParam String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return SysResponse.fail("登录失败，用户名或密码不正确！");
        }
        String principal = (String) subject.getPrincipal();
        if (username.equals(principal)) {//同一用户登录
            return SysResponse.ok("登录成功");
        }
        subject.logout();//否则,原用户退出登录，新用户登录
        try {
            subject.login(new UsernamePasswordToken(username, password));
            return SysResponse.ok("登录成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return SysResponse.fail("登录失败，用户名或密码不正确！");
        }
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
        return SysResponse.fail("Haven't logined");
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
