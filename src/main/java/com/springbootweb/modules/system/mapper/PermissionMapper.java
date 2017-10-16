package com.springbootweb.modules.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springbootweb.modules.system.pojo.Permission;

public interface PermissionMapper extends BaseMapper<Permission> {

	List<Permission> listByUsername(String username);

}
