package com.jusfoun.config.file;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 描述 : 系统的一些常量的配置. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月16日 下午1:40:24
 */
@Configuration
@ConfigurationProperties(prefix = UrlsConfig.PREFIX)
public class UrlsConfig {
	public static final String PREFIX = "system.urls";

	private String baseUrl;
	private String baseLoginUrl;
	private String inputOriginUrl;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBaseLoginUrl() {
		return baseLoginUrl;
	}

	public String getBaseLoginUrl(Object... args) {
		return String.format(baseLoginUrl, args);
	}

	public void setBaseLoginUrl(String baseLoginUrl) {
		this.baseLoginUrl = baseLoginUrl;
	}

	public String getInputOriginUrl() {
		return inputOriginUrl;
	}

	public String getInputOriginUrl(Object... args) {
		return String.format(inputOriginUrl, args);
	}

	public void setInputOriginUrl(String inputOriginUrl) {
		this.inputOriginUrl = inputOriginUrl;
	}

}
