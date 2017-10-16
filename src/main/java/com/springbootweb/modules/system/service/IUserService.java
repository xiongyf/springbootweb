package com.springbootweb.modules.system.service;

import java.util.List;

import com.springbootweb.common.utils.ResponsePage;
import com.springbootweb.modules.system.pojo.User;

public interface IUserService {

	User selectUserById(String id);

	User getUserByName(String name);

	List<User> findByPage(int startPage, int rowsPerPage);

	ResponsePage<User> getUsersByName(String name);

	User addUser(User user);
}
