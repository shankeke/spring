package com.jusfoun.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 说明：模板配置信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年6月15日 上午9:22:09
 */
@Configuration
@ConfigurationProperties(prefix = InitConfig.PREFIX)
public class InitConfig {
	public static final String PREFIX = "system.init";

	/**
	 * 初始化账户
	 */
	private String username;
	
	/**
	 * 初始化密码
	 */
	private String password;

	/**
	 * 系统权限初始化文件
	 */
	private String sysModulesFile;

	/**
	 * 客户端信息初始化文件
	 */
	private String clientDetailsFile;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSysModulesFile() {
		return sysModulesFile;
	}

	public void setSysModulesFile(String sysModulesFile) {
		this.sysModulesFile = sysModulesFile;
	}

	public String getClientDetailsFile() {
		return clientDetailsFile;
	}

	public void setClientDetailsFile(String clientDetailsFile) {
		this.clientDetailsFile = clientDetailsFile;
	}

}
