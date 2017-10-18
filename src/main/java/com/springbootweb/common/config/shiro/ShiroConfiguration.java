package com.springbootweb.common.config.shiro;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ShiroConfiguration 
 * @Description: shiro权限管理配置
 */
@Configuration
public class ShiroConfiguration {

	/**
	 * 保证实现了Shiro内部lifecycle函数的bean执行
	 * 
	 * @return
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * AOP式方法级权限检查
	 * 
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			DefaultWebSecurityManager defaultWebSecurityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * 安全管理器
	 * 
	 * @return
	 */
	@Bean(name = "defaultWebSecurityManager")
	public DefaultWebSecurityManager defaultWebSecurityManager(SystemAuthorizingRealm systemAuthorizingRealm,
			EhCacheManager ehCacheManager, DefaultWebSessionManager defaultWebSessionManager) {
		DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
		defaultWebSecurityManager.setRealm(systemAuthorizingRealm);
		defaultWebSecurityManager.setCacheManager(ehCacheManager);
		defaultWebSecurityManager.setSessionManager(defaultWebSessionManager);
		return defaultWebSecurityManager;
	}

	/**
	 * 加载系统自定义配置的权限控制，并设置缓存管理
	 * 
	 * @param ehCacheManager
	 * @return
	 */
	@Bean(name = "systemAuthorizingRealm")
	public SystemAuthorizingRealm systemAuthorizingRealm(EhCacheManager ehCacheManager,
			CredentialsMatcher credentialsMatcher) {
		SystemAuthorizingRealm systemAuthorizingRealm = new SystemAuthorizingRealm();
		systemAuthorizingRealm.setCacheManager(ehCacheManager);
		systemAuthorizingRealm.setCredentialsMatcher(credentialsMatcher);
		return systemAuthorizingRealm;
	}

	/**
	 * 
	 * 对密码进行编码
	 * 
	 * @return
	 */
	@Bean(name = "credentialsMatcher")
	public CredentialsMatcher credentialsMatcher() {
		CredentialsMatcher credentialsMatcher = new SimpleCredentialsMatcher() {
			@Override
			public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
				char[] credential = (char[]) token.getCredentials();
				String inputPassword = String.valueOf(credential);
				String realPassword = (String) info.getCredentials();
				return inputPassword.equals(realPassword);
			}
		};
		return credentialsMatcher;
	}

	/**
	 * 采用EhCache缓存管理
	 * 
	 * @return
	 */
	@Bean(name = "ehCacheManager")
	public EhCacheManager ehCacheManager() {
		EhCacheManager ehCacheManager = new EhCacheManager();
		return ehCacheManager;
	}

	/**
	 * 安全认证过滤器
	 * 
	 * @return
	 */
	@Bean(name = "shiroFilterFactoryBean")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
		shiroFilterFactoryBean.setLoginUrl("/login.html");//未登录时重定向
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

		// 设置资源权限过滤
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/user/login", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		shiroFilterFactoryBean.setFilters(filters);

		return shiroFilterFactoryBean;
	}

	/**
	 * 基于Form表单的身份验证过滤器
	 * 
	 * @return
	 */
	@Bean(name = "systemFormAuthenticationFilter")
	public SystemFormAuthenticationFilter systemFormAuthenticationFilter() {
		SystemFormAuthenticationFilter systemFormAuthenticationFilter = new SystemFormAuthenticationFilter();
		systemFormAuthenticationFilter.setLoginUrl("/user/login");
		return systemFormAuthenticationFilter;
	}

	/*****************************************************************/
	/**
	 * 会话ID生成器
	 * 
	 * @return
	 */
	@Bean(name = "javaUuidSessionIdGenerator")
	public JavaUuidSessionIdGenerator javaUuidSessionIdGenerator() {
		return new JavaUuidSessionIdGenerator();
	}

	/**
	 * 会话DAO，需要缓存实现
	 * 
	 * @return
	 */
	@Bean(name = "enterpriseCacheSessionDAO")
	public EnterpriseCacheSessionDAO enterpriseCacheSessionDAO(JavaUuidSessionIdGenerator javaUuidSessionIdGenerator,
			EhCacheManager ehCacheManager) {
		EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
		sessionDAO.setSessionIdGenerator(javaUuidSessionIdGenerator);
		// sessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
		sessionDAO.setCacheManager(ehCacheManager);
		return sessionDAO;
	}

	/**
	 * 内存会话DAO，重启session会丢失
	 * 
	 * @return
	 */
	@Bean(name = "memorySessionDAO")
	public MemorySessionDAO memorySessionDAO(JavaUuidSessionIdGenerator javaUuidSessionIdGenerator,
			EhCacheManager ehCacheManager) {
		MemorySessionDAO sessionDAO = new MemorySessionDAO();
		sessionDAO.setSessionIdGenerator(javaUuidSessionIdGenerator);
		return sessionDAO;
	}

	/**
	 * 会话Cookie模板
	 * 
	 * @return
	 */
	@Bean(name = "simpleCookie")
	public SimpleCookie simpleCookie() {
		SimpleCookie simpleCookie = new SimpleCookie();
		simpleCookie.setHttpOnly(true);
		simpleCookie.setMaxAge(-1);
		simpleCookie.setName("SID");
		return simpleCookie;
	}

	// /**
	// * 会话验证调度器
	// *
	// * @return
	// */
	// @Bean(name = "executorServiceSessionValidationScheduler")
	// public ExecutorServiceSessionValidationScheduler
	// executorServiceSessionValidationScheduler(
	// DefaultWebSessionManager defaultWebSessionManager) {
	// ExecutorServiceSessionValidationScheduler sessionValidationScheduler =
	// new ExecutorServiceSessionValidationScheduler();
	// sessionValidationScheduler.setInterval(18000L);
	// sessionValidationScheduler.setSessionManager(defaultWebSessionManager);
	// return sessionValidationScheduler;
	// }

	/**
	 * 会话管理器
	 * 
	 * @return
	 */
	@Bean(name = "defaultWebSessionManager")
	public DefaultWebSessionManager defaultWebSessionManager(SimpleCookie simpleCookie,
			MemorySessionDAO memorySessionDAO, ShiroSessionListener shiroSessionListener) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(1800000L); // 会话超时
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true); // 开启默认的会话验证调度器
		sessionManager.setSessionValidationInterval(1800000L); // 会话验证间隔
		sessionManager.setSessionDAO(memorySessionDAO);
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdCookie(simpleCookie);
		Collection<SessionListener> sessionListeners = sessionManager.getSessionListeners();
		sessionListeners.add(shiroSessionListener);
		sessionManager.setSessionListeners(sessionListeners);
		return sessionManager;
	}

}
