
package com.springbootweb.modules.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.springbootweb.modules.system.pojo.Role;

public interface RoleMapper extends BaseMapper<Role> {

	List<Role> listByUsername(String username);
}
