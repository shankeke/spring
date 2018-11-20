package com.jusfoun.common.message.jackson.fieldFilter;

import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jusfoun.common.message.annotation.JsonBody;

/**
 * 说明： jackson序列化处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月25日 上午9:56:03
 */
public class FilterFieldsJsonSerializer {
	private FilterFieldsJsonFilter jacksonFilter = new FilterFieldsJsonFilter();
	private ObjectMapper objectMapper;

	public FilterFieldsJsonSerializer(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	/**
	 * 说明： 过滤逻辑. <br>
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
	 * 说明： 执行过滤. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月25日 上午9:59:00
	 * @param filter
	 *            过滤的json注解
	 */
	public void filter(JsonBody filter) {
		this.filter(filter.type(), filter.includes(), filter.excludes());
	}

	/**
	 * 说明：返回json. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月25日 上午9:58:40
	 * @param object
	 *            返回对象
	 * @return 序列化字符串
	 * @throws JsonProcessingException
	 */
	public String toJson(Object object) throws JsonProcessingException {
		objectMapper.setFilterProvider(jacksonFilter);
		return objectMapper.writeValueAsString(object);
	}

	/**
	 * 说明：返回json. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月25日 上午9:58:40
	 * @param resultFile
	 *            输出文件
	 * @param object
	 *            返回对象
	 * @throws IOException
	 */
	public void toJson(File resultFile, Object value) throws IOException {
		objectMapper.setFilterProvider(jacksonFilter);
		objectMapper.writeValue(resultFile, value);
	}

	/**
	 * 说明：返回json. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月25日 上午9:58:40
	 * @param out
	 *            输出流
	 * @param object
	 *            返回对象
	 * @throws IOException
	 */
	public void toJson(OutputStream out, Object value) throws IOException {
		objectMapper.setFilterProvider(jacksonFilter);
		objectMapper.writeValue(out, value);
	}

	/**
	 * 说明：返回json. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月25日 上午9:58:40
	 * @param out
	 *            输出流
	 * @param object
	 *            返回对象
	 * @throws IOException
	 */
	public void toJson(DataOutput out, Object value) throws IOException {
		objectMapper.setFilterProvider(jacksonFilter);
		objectMapper.writeValue(out, value);
	}
}