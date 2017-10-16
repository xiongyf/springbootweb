package com.springbootweb.modules.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.springbootweb.common.utils.ResponsePage;
import com.springbootweb.modules.system.mapper.UserMapper;
import com.springbootweb.modules.system.pojo.User;
import com.springbootweb.modules.system.service.IUserService;

/**
 * Created by Administrator on 2017/3/17.
 */

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User selectUserById(String id) {
		User user = userMapper.selectById(id);
		return user;
	}

	@Override
	public User getUserByName(String name) {
		return userMapper.getUserByName(name);
	}

	@Override
	public List<User> findByPage(int startPage, int rowsPerPage) {
		PageHelper.startPage(startPage, rowsPerPage);
		List<User> list = userMapper.selectList(null);
		PageHelper.remove();
		return list;
	}

	@Override
	public ResponsePage<User> getUsersByName(String name) {
		PageHelper.startPage(1, 10);
		List<User> users = userMapper.getUsersByName(name);
		System.out.println(users.size());
		return new ResponsePage<User>(users);
	}

	@Override
	public User addUser(User user) {
		userMapper.insert(user);
		return user;
	}

}
