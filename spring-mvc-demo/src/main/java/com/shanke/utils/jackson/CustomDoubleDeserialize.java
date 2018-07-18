package com.shanke.utils.jackson;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * <pre>
 * Description: 
 *        自定义double类型反序列化类
 * </pre>
 * 
 * Copyright (c) 2015 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2015年12月29日 下午5:48:10
 * @version 1.0
 */
public class CustomDoubleDeserialize extends JsonDeserializer<Double> {

	private DecimalFormat df = new DecimalFormat("##.00");

	@Override
	public Double deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Number parse = null;
		try {
			parse = df.parse(jp.getText());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse.doubleValue();
	}
}
