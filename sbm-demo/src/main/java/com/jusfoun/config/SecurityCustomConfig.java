package com.jusfoun.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:自定义的Security配置信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年6月15日 上午9:22:09
 */
@Configuration
@ConfigurationProperties(prefix = SecurityCustomConfig.PREFIX)
public class SecurityCustomConfig {
	public static final String PREFIX = "security.custom";

	/**
	 * 忽略的静态资源请求
	 */
	private String[] ignoredStaticResources;

	/**
	 * 忽略的GET请求
	 */
	private String[] ignoredGetResources;

	/**
	 * 忽略的POST请求
	 */
	private String[] ignoredPostResources;

	public String[] getIgnoredStaticResources() {
		return ignoredStaticResources;
	}

	public void setIgnoredStaticResources(String[] ignoredStaticResources) {
		this.ignoredStaticResources = ignoredStaticResources;
	}

	public String[] getIgnoredGetResources() {
		return ignoredGetResources;
	}

	public void setIgnoredGetResources(String[] ignoredGetResources) {
		this.ignoredGetResources = ignoredGetResources;
	}

	public String[] getIgnoredPostResources() {
		return ignoredPostResources;
	}

	public void setIgnoredPostResources(String[] ignoredPostResources) {
		this.ignoredPostResources = ignoredPostResources;
	}
}
