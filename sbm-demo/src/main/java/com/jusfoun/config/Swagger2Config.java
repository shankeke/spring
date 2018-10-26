package com.jusfoun.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
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
 * 说明：WAGGER2配置. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月26日 下午2:40:55
 */
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix = Swagger2Config.PREFIX)
public class Swagger2Config {

	public static final String PREFIX = "swagger";

	private boolean enable;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.enable(enable)//
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
						+ "1、Spring Boot使用：利用Spring Boot构建项目， 运用其特性，整合多种框架，扩展相关接口实现定制功能，如：Mybatis、Mybatis自动映射Mapper框架、自动分页框架PageHelper、Redis客户端驱动Lettuce、数据源驱动HikariCP等。\n"//
						+ "2、Mybatis扩展：整合Mybatis自动映射框架，实现丰富的增删改查接口满足绝大多数持久化操作需求，基于内存的Xml文件生成，规避手动或自动生成的大量Xml代码文件，减少凌乱重复的代码，避免了手工修改代码等失误，节省大量开发时间。\n"//
						+ "3、数据源：支持多数据源，源源之间无缝切换，对开发人员透明化，无需关心当前具体使用的数据源实例，数据业务低耦合。 \n"//
						+ "4、安全认证：定制扩展Spring Security接口实现前后端分离的授权鉴权，支持多客户端、多令牌类型、多认证形式。\n"//
						+ "5、设计模式：利用多种常见设计模式，贯彻面向接口编程思想，优化代码结构，构建高质量代码；尽量的减少重复代码，提高代码的简洁度和可读性。\n"//
						+ "6、切面处理：充分利用Spring的Aop等技术实现访问日志拦截、模式化对象的数据库操作预处理逻辑、用户权限控制、消息标准化等功能。 \n"//
						+ "7、报文标准：接口报文使用标准模板封装，借助Jackson序列化和反序列化技特性定制报文字段，利用泛型实现，灵活度更高，代码更规范，开发更简单，配合Swagger2技术，使接口文档规范好用。\n"//
						+ "8、常量定义：利用枚举常量，充分利用枚举特性丰富常量API，使常量引用更灵活，代码更规范（很多人不定义常量，代码可读性差，这种代码最让人恶心），如：利用枚举继承特性扩展枚举比较大小的功能；利用枚举属性定义的特性扩展方法等。 \n"//
						+ "9、自动分页：自动包装分页信息，标准化分页数据结构。 \n"//
						+ "10、缓存使用：整合Redis数据，利用redis作缓存，配合spring缓存注解，低耦合实现缓存需求。 \n"//
						+ "11、Jdk新特性：使用Jdk 1.8新特性解决一些特殊需求，展现一些API使用技巧和使用场景，给新手学习提供范例，如：接口默认方法、Lambda表达式、Stream等。 \n"//
						+ "12、树结构数据处理：面向常见的应用场景，对于符合树状结构的数据抽象数据模型，抽象查询接口，结构化组合关系，以模式化代码实现树状结构数据的操作、检索等功能。 \n"//
						+ "13、Excel导入导出：使用反射技术，抽象Excel数据模型实现面向Bean对象的数据导入接口。引用Freemaker模板技术，实现模板化导出功能，可实现复杂的数据结构导出功能，代码量更少，开发更简单，结构更优化，如：横向纵向合并单元格，多sheet数据填写等。\n"//
						+ "14、Jaxb支持：完善的Jaxb序列化和反序列化工具，支持复杂的Xml报文与Java Bean互转功能。\n"//
						+ "15、其他：二维码处理、文件上传统一接口、Spring Profiles使用、Spring Boot CommandLineRunner使用、Spel使用、定时任务等。\n"//
						+ "更多相关示例请关注：https://github.com/760374564/spring/tree/master/sbm-demo")//
				.termsOfServiceUrl("https://github.com/760374564/")//
				.version("1.0")//
				.build();
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}