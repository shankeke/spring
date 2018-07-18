//package com.shanke.web.conf;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import com.shanke.web.interceptor.MyInterceptor1;
//
///**
// * 描述 :spring mvc 配置注册. <br>
// * <p>
// * 
// * Copyright (c) 2016 优识云创(YSYC)
// * 
// * @author Uknower-yjw
// * @date 2016年7月30日 下午11:23:59
// */
//@Configuration
//public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
//	private static final Logger logger = LoggerFactory.getLogger(MyWebAppConfigurer.class);
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		// 多个拦截器组成一个拦截器链
//		// addPathPatterns 添加拦截规则
//		// excludePathPatterns 排除拦截规则
//		logger.info("spring boot 注册拦截器");
//		registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
//		// registry.addInterceptor(new
//		// MyInterceptor2()).addPathPatterns("/**").excludePathPatterns("/user/*");
//		super.addInterceptors(registry);
//	}
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		// registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");
//		// 访问myres根目录下的fengjing.jpg 的URL为 http://localhost:8080/fengjing.jpg（/**
//		// 会覆盖系统默认的配置）
//		logger.info("spring boot 注册静态资源路径");
//		registry.addResourceHandler("/**")//
//				.addResourceLocations("classpath:/myres/")//
//				.addResourceLocations("classpath:/static/")//
//				.addResourceLocations("classpath:/img1/", "classpath:/img2/", "classpath:/img3/")// 参数是动参，可以这样写
//		;
//		super.addResourceHandlers(registry);
//	}
//
// }