package com.jusfoun.config.file;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;

import com.jusfoun.config.file.progress.CustomMultipartResolver;

@Configuration
public class MultipartConfig {
	/*
	 * 将 multipartResolver 指向我们刚刚创建好的继承 CommonsMultipartResolver 类的自定义文件上传处理类
	 */
	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver() {
		return new CustomMultipartResolver();
	}
}
