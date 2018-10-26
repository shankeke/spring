package com.jusfoun.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明：spring boot 处理listener.<br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午11:09:11
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("ServletContext初始化" + sce.getServletContext().getContextPath() + " ,"
				+ sce.getServletContext().getServerInfo());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("ServletContext销毁" + sce.getServletContext().getContextPath() + " ,"
				+ sce.getServletContext().getServerInfo());
	}

}
