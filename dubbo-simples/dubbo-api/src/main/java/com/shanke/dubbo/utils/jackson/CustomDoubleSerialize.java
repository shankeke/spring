package com.shanke.dubbo.utils.jackson;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * <pre>
 * Description: 
 *       自定义双精度数据序列化类
 * </pre>
 * 
 * Copyright (c) 2015 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2015年12月29日 下午5:48:49
 * @version 1.0
 */
public class CustomDoubleSerialize extends JsonSerializer<Double> {

	private DecimalFormat df = new DecimalFormat("##.00");

	@Override
	public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jgen.writeString(df.format(value));
	}
}
