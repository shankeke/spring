package com.jusfoun.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述 : spring boot 处理session listener. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午11:08:47
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
	private static final Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.info("Session 被创建" + se.getSession().getId() + " ," + se.getSession().toString());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("Session 被销毁：" + se.getSession().getId() + " ," + se.getSession().toString());
	}
}
