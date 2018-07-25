package com.jusfoun.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:模板配置信息. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年6月15日 上午9:22:09
 */
@Configuration
@ConfigurationProperties(prefix = TemplatesConfig.PREFIX)
public class TemplatesConfig {
	public static final String PREFIX = "templates";

	/**
	 * 模板存放路径
	 */
	private String dir;

	/**
	 * 国家信息模板文件名称
	 */
	private String countries;

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getCountries() {
		return countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

}
