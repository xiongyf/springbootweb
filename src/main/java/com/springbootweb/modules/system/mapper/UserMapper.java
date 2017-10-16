package com.springbootweb.modules.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springbootweb.modules.system.pojo.User;

public interface UserMapper extends BaseMapper<User> {

	User getUserByName(String name);

	List<User> getUsersByName(String name);
}
