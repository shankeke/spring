package com.shanke.utils.jackson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * <pre>
 * Description: 
 *       自定义Date类型数据序列化类
 * </pre>
 * 
 * Copyright (c) 2015 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2015年12月29日 下午5:48:49
 * @version 1.0
 */
public class CustomDateSerialize extends JsonSerializer<Date> {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeString(sdf.format(value));
	}
}
