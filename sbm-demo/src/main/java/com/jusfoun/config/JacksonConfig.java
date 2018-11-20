package com.jusfoun.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 说明：Jackson序列化属性配置. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月23日 下午8:24:37
 */
@Configuration
public class JacksonConfig {

	/**
	 * 说明：统一的ObjectMapper实例，系统在哪里需要ObjectMapper的时候都引用该实例以实现json数据的统一规范. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月25日 上午11:57:34
	 * @param builder
	 *            构建器
	 * @return ObjectMapper实例
	 */
	@Bean
	@Primary
	@ConditionalOnMissingBean(ObjectMapper.class)
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		// 设置json为空字段时的序列化方式
		objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
				jsonGenerator.writeString("");
			}
		});
		return objectMapper;
	}
}
