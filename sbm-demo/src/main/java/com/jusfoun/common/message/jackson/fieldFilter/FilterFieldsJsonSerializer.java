package com.jusfoun.common.message.jackson.fieldFilter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jusfoun.common.message.annotation.Json;

/**
 * 描述 : jackson序列化处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月25日 上午9:56:03
 */
public class FilterFieldsJsonSerializer {
	private static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private FilterFieldsJsonFilter jacksonFilter = new FilterFieldsJsonFilter();
	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		// 设置json为空字段时的序列化方式
		objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
				jsonGenerator.writeString("");
			}
		});

		// 去掉默认的时间戳格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 序列化时，日期的统一格式
		objectMapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATE_FORMAT_PATTERN));
		// 设置为中国上海时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		// 空值不序列化
		// objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES,
		// false);//属性已弃用
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 反序列化时，属性不存在的兼容处理
		objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 单引号处理
		objectMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
	}

	/**
	 * 描述 : 过滤逻辑. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月25日 上午9:56:16
	 * @param clazz
	 *            过滤类型
	 * @param includeFields
	 *            包含的字段
	 * @param excludeFields
	 *            过滤掉的字段
	 */
	public void filter(Class<?> clazz, String[] includeFields, String[] excludeFields) {
		if (clazz == null)
			return;
		if (includeFields != null && includeFields.length > 0) {
			jacksonFilter.include(clazz, includeFields);
		}
		if (excludeFields != null && excludeFields.length > 0) {
			jacksonFilter.exclude(clazz, excludeFields);
		}
		objectMapper.addMixIn(clazz, jacksonFilter.getClass());
	}

	/**
	 * 描述 :返回json. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月25日 上午9:58:40
	 * @param object
	 *            返回对象
	 * @return
	 * @throws JsonProcessingException
	 */
	public String toJson(Object object) throws JsonProcessingException {
		objectMapper.setFilterProvider(jacksonFilter);
		return objectMapper.writeValueAsString(object);
	}

	/**
	 * 描述 : 执行过滤. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月25日 上午9:59:00
	 * @param filter
	 *            过滤的json注解
	 */
	public void filter(Json filter) {
		this.filter(filter.type(), filter.includes(), filter.excludes());
	}
}