package com.shanke.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述 : spring boot 处理listener. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016年7月30日 下午10:55:09
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory
			.getLogger(MyServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("ServletContext初始化"
				+ sce.getServletContext().getContextPath() + " ,"
				+ sce.getServletContext().getServerInfo());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("ServletContext销毁"
				+ sce.getServletContext().getContextPath() + " ,"
				+ sce.getServletContext().getServerInfo());
	}
}
