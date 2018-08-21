package com.jusfoun.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.jusfoun.common.message.jackson.fieldFilter.FilterFieldsJsonReturnHandler;
import com.jusfoun.config.file.FileConfig;
import com.jusfoun.web.filter.gzip.GzipSupportFilter;
import com.jusfoun.web.interceptor.FileTypeInterceptor;

/**
 * 描述 : web属性配置. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:24:03
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {

	@Autowired
	private FileConfig fileConfig;

	@Bean
	public ServletListenerRegistrationBean<RequestContextListener> servletListenerRegistrationBean() {
		return new ServletListenerRegistrationBean<RequestContextListener>(new RequestContextListener());
	}

	// 注册一个json结果处理器
	@Bean
	public HandlerMethodReturnValueHandler filterFieldsJsonReturnHandler() {
		return new FilterFieldsJsonReturnHandler();// 初始化json处理器
	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		// returnValueHandlers.add(filterFieldsJsonReturnHandler());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String filesPath = "file:" + fileConfig.getRoot() + fileConfig.getRootDir();
		registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/public/", filesPath);
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) { // 多个拦截器组成一个拦截器链
		// 注册文件类型过滤拦截器
		super.addInterceptors(registry);
		registry.addInterceptor(new FileTypeInterceptor(fileConfig.getSuffix())).addPathPatterns("/file/**");
	}

	/**
	 * 跨域访问过滤配置
	 *
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry)
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// registry//
		// .addMapping("/**")//
		// .allowedOrigins("*")//
		// .allowCredentials(true)//
		// .allowedHeaders("X-Requested-With", "X-Custom-Header",
		// "Authorization", "Content-Type", "Accept",
		// "Origin")//
		// .allowedMethods("GET", "OPTIONS", "PATCH", "POST", "DELETE", "PUT")//
		// .maxAge(3600);
	}

	/**
	 * 描述 : 注册一个自定义的通信数据压缩的过滤器. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月1日 下午2:06:52
	 * @return 注册的bean
	 */
	@Bean
	public FilterRegistrationBean<GzipSupportFilter> gzipSupportFilterRegistrationBean() {
		FilterRegistrationBean<GzipSupportFilter> registrationBean = new FilterRegistrationBean<GzipSupportFilter>(new GzipSupportFilter());
		registrationBean.addUrlPatterns("/**");
		return registrationBean;
	}
}
