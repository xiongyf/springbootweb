package com.springbootweb.common.config.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.springbootweb.modules.system.pojo.User;
import com.springbootweb.modules.system.service.IPermissionService;
import com.springbootweb.modules.system.service.IRoleService;
import com.springbootweb.modules.system.service.IUserService;

/**
 * @ClassName: SystemAuthorizingRealm 
 * @Description: 权限控制配置
 * @date: 2017年2月6日 下午2:13:41 
 *
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

	@Resource(name = "userService")
	private IUserService userService;

	@Resource(name = "roleService")
	private IRoleService roleService;

	@Resource(name = "permissionService")
	private IPermissionService permissionService;

	/**
	 * 权限认证，数据库获取角色和权限的字符串，与@RequiresPermissions所配置的字符串验证是否有访问权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String username = (String) super.getAvailablePrincipal(principalCollection);
		List<String> roleCodes = roleService.listCodeByUsername(username);
		List<String> permissionCodes = permissionService.listCodeByUsername(username);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRoles(roleCodes);
		authorizationInfo.addStringPermissions(permissionCodes);
		return authorizationInfo;
	}

	/**
	 * 登录认证，认证用户的账户密码
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.getUserByName(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
				user.getPassword(), getName());
		return simpleAuthenticationInfo;
	}

}
