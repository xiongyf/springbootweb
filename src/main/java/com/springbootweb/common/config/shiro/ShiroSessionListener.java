package com.springbootweb.common.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ShiroSessionListener 
 * @Description: 监听session注销或者超时
 * @date: 2017年2月6日 下午2:13:04 
 *
 */
@Component
public class ShiroSessionListener implements SessionListener {

	@Override
	public void onExpiration(Session session) {
	}

	@Override
	public void onStart(Session session) {
	}

	@Override
	public void onStop(Session session) {
	}

}
