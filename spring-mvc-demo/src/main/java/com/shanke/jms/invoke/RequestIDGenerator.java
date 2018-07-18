package com.shanke.jms.invoke;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 描述 :自定义JMSCorrelationID生成策略. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-9-1 下午2:38:42
 */
public class RequestIDGenerator {

	public static String generateMessageRequestID() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmssSSS");
		String id = simpleDateFormat.format(new Date());
		String numberChar = "0123456789";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
		}
		return id + sb.toString();
	}
}
