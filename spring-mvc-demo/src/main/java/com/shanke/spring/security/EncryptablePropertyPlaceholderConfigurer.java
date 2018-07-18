package com.shanke.spring.security;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.shanke.utils.encrypt.Des;

/**
 * 描述 : 自定义spring配置文件读取逻辑. <br>
 * 1、对需要的参数进行加解密，这里进行解密加载到
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-7-4 上午9:03:49
 */
public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private static final String key = "0001000200030004";

	// jdbc.driver=oracle.jdbc.driver.OracleDriver
	// jdbc.url=jdbc:oracle:thin:@localhost:1521:orcl
	// jdbc.username=JXTEST
	// jdbc.password=JXTEST

	@SuppressWarnings("unused")
	private static final String jdbc_driver = "jdbc.driver";
	@SuppressWarnings("unused")
	private static final String jdbc_url = "jdbc.url";
	private static final String jdbc_username = "jdbc.username";
	private static final String jdbc_password = "jdbc.password";

	@SuppressWarnings("static-access")
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		try {
			Des des = new Des();

			// String url = props.getProperty(jdbc_url);
			// if (url != null) {
			// props.setProperty(jdbc_url, des.Decrypt(url, des.hex2byte(key)));
			// }
			// String driverClassName = props.getProperty(jdbc_driver);
			// if (driverClassName != null) {
			// props.setProperty(jdbc_driver,
			// des.Decrypt(driverClassName, des.hex2byte(key)));
			// }

			String username = props.getProperty(jdbc_username);
			if (username != null) {
				props.setProperty(jdbc_username, des.Decrypt(username, des.hex2byte(key)));
			}

			String password = props.getProperty(jdbc_password);
			if (password != null) {
				props.setProperty(jdbc_password, des.Decrypt(password, des.hex2byte(key)));
			}
			super.processProperties(beanFactory, props);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BeanInitializationException(e.getMessage());
		}
	}
}
