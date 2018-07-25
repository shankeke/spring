package com.jusfoun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.apiInfo(apiInfo())//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("com.jusfoun"))//
				.paths(PathSelectors.any())//
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()//
				.title("Spring Boot整合mybatis项目示例")//
				.description("知识点：\n"//
						+ "1、Spring Boot使用：大量运用spring boot 的特性，扩展相关需要整合的功能。\n"//
						+ "2、Mybatis扩展：自动化的映射接口，规避手动实现xml映射，自动实现丰富的增删改查接口满足绝大多数持久化操作功能，节省大量开发时间，减少凌乱重复的代码，避免了手工代码的bug。\n"//
						+ "3、自动分页技术：自动包装分页信息，标准化分页数据结构。\n"//
						+ "4、Excel导出：引用Freemaker模板技术，实现模板化导出功能。\n"//
						+ "5、安全认证：定制扩展Spring Security实现前后端分离的授权鉴权，支持多客户端、多令牌类型、多认证形式。\n"//
						+ "6、设计模式：充分利用设计模式，优化代码结构，构建高质量代码。\n"//
						+ "7、Jdk特性：大量使用Jdk新特性，高效实现一些特殊的逻辑需求。\n"//
						+ "更多相关示例请关注：https://github.com/760374564/")//
				.termsOfServiceUrl("https://github.com/760374564/")//
				.version("1.0")//
				.build();
	}

}