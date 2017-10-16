
package com.springbootweb.modules.system.service;

import java.util.List;

import com.springbootweb.modules.system.pojo.Permission;

public interface IPermissionService {

	List<Permission> listByUsername(String username);

	List<String> listCodeByUsername(String username);
}
