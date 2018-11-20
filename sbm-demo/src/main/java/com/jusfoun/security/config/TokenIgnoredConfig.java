package com.jusfoun.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 说明：自定义的Security配置信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年6月15日 上午9:22:09
 */
@Configuration
@ConfigurationProperties(prefix = TokenIgnoredConfig.PREFIX)
public class TokenIgnoredConfig {
	public static final String PREFIX = "security.token.ignored";

	/**
	 * 忽略的静态资源请求
	 */
	private String[] staticResources;

	/**
	 * 所有方法都忽略的请求
	 */
	private String[] anyMethods;

	/**
	 * 忽略的GET请求
	 */
	private String[] getMethods;

	/**
	 * 忽略的POST请求
	 */
	private String[] postMethods;

	public String[] getStaticResources() {
		return staticResources;
	}

	public void setStaticResources(String[] staticResources) {
		this.staticResources = staticResources;
	}

	public String[] getAnyMethods() {
		return anyMethods;
	}

	public void setAnyMethods(String[] anyMethods) {
		this.anyMethods = anyMethods;
	}

	public String[] getGetMethods() {
		return getMethods;
	}

	public void setGetMethods(String[] getMethods) {
		this.getMethods = getMethods;
	}

	public String[] getPostMethods() {
		return postMethods;
	}

	public void setPostMethods(String[] postMethods) {
		this.postMethods = postMethods;
	}

}
