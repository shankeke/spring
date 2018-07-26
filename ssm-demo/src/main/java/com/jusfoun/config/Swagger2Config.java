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

/**
 * 描述:WAGGER2配置. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月26日 下午2:40:55
 */
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
				.title("Spring Boot整合Mybatis项目示例")//
				.description("知识点：\n"//
						+ "1、Spring Boot使用：大量运用spring boot 的特性，扩展相关需要整合的功能。\n"//
						+ "2、Mybatis扩展：自动化的映射接口，规避手动实现Xml映射，自动实现丰富的增删改查接口满足绝大多数持久化操作功能，节省大量开发时间，减少凌乱重复的代码，避免了手工代码的失误。\n"//
						+ "3、自动分页技术：自动包装分页信息，标准化分页数据结构。\n"//
						+ "4、Jdk特性：大量使用Jdk新特性，高效实现一些特殊的逻辑需求。\n"//
						+ "5、安全认证：定制扩展Spring Security实现前后端分离的授权鉴权，支持多客户端、多令牌类型、多认证形式。\n"//
						+ "6、设计模式：充分利用设计模式，优化代码结构，构建高质量代码；大量使用抽象类和接口，实现一些相似业务功能，去除大量重复代码，增加代码的简洁度和可读性。\n"//
						+ "7、树状数据处理：对于符合树状结构的数据抽象数据模型，抽象查询接口，结构化组合关系，以模式化代码实现树状结构数据的操作、检索等。\n"//
						+ "8、Excel导出：引用Freemaker模板技术，实现模板化导出功能，可实现复杂的数据结构导出功能，代码量更少，开发更简单，结构更优化，如：横向纵向合并单元格，数据分SHEET等。\n"//
						+ "9、数据源：支持多数据源，源源之间无缝切换，对开发人员透明化，无需关心当前具体使用的数据源实例，数据业务低耦合。\n"//
						+ "10、报文标准：接口报文使用标准模板封装，借助Jackson序列化和反序列化技特性定制报文字段，利用泛型实现，灵活度更高，代码更规范，开发更简单，配合SWAGGER技术，完美展示接口文档。\n"//
						+ "更多相关示例请关注：https://github.com/760374564/spring/tree/master/ssm-demo")//
				.termsOfServiceUrl("https://github.com/760374564/")//
				.version("1.0")//
				.build();
	}

}