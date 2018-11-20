package com.jusfoun.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;
import com.jusfoun.security.config.WebSecurityConfig;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
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
				.build() //
				.globalOperationParameters(globalOperationParameters());//
	}

	/**
	 * 说明：添加公共参数. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年11月15日 下午2:53:25
	 * @return 公共参数列表
	 */
	private List<Parameter> globalOperationParameters() {
		List<Parameter> pars = Lists.newArrayList();
		ParameterBuilder tokenPar = new ParameterBuilder();
		// 头信息
		tokenPar//
				.name(WebSecurityConfig.TOKEN_HEADER_PARAM)//
				.description("令牌")//
				.modelRef(new ModelRef("string"))//
				.parameterType("header")//
				.required(false)//
				.build();
		pars.add(tokenPar.build());
		return pars;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()//
				.title("潍坊农业大数据API文档")//
				.description("潍坊农业大数据项目")//
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