package com.shanke.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述 :spring boot 处理session listener. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016年7月30日 下午10:56:41
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
