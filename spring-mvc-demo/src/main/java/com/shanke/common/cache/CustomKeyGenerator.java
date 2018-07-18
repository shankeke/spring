package com.shanke.common.cache;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;

/**
 * 描述 : 自定义spring cache key生成策略. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-3-31 下午12:05:29
 */
public class CustomKeyGenerator implements KeyGenerator {

	private final Logger log = LoggerFactory
			.getLogger(CustomKeyGenerator.class);

	/*
	 * @Override public Object generate(Object target, Method method, Object...
	 * params) { StringBuilder sb = new StringBuilder(); // 添加class名称
	 * sb.append(target.getClass().getName() + "."); // 添加方法名称
	 * sb.append(method.getName() + "."); // 添加参数 for (Object obj : params) {
	 * sb.append(obj.toString()); } log.debug("生成缓存key值：" + sb.toString());
	 * return sb.toString(); }
	 */

	@Override
	public Object generate(Object target, Method method, Object... params) {
		// Object keyGenerator = super.generate(target, method, params);

		StringBuffer sb = new StringBuffer();
		// Class entityClass =
		// GenericsHelper.getSuperGenericsClass(target.getClass());
		// 添加class名称
		sb.append(target.getClass().getName() + ".");
		// 添加方法名称
		sb.append(method.getName() + ".");
		// 添加参数
		if (params != null && params.length > 0) {
			for (Object obj : params) {
				if (obj != null) {
					if (obj instanceof AtomicInteger
							|| obj instanceof AtomicLong
							|| obj instanceof BigDecimal
							|| obj instanceof BigInteger || obj instanceof Byte
							|| obj instanceof Double || obj instanceof Float
							|| obj instanceof Integer || obj instanceof Long
							|| obj instanceof Short) {
						sb.append("_" + obj);
					} else if (obj instanceof List || obj instanceof Set
							|| obj instanceof Map) {
						// sb.append("_" + obj);
						sb.append("_" + obj);
					} else {
						sb.append("_" + obj.hashCode());
						// sb.append("_" + obj.toString());
					}
				}
			}
		}
		// System.out.println(sb.toString());
		int keyGenerator = sb.toString().hashCode();
		log.debug(sb.toString() + ":" + keyGenerator);
		return keyGenerator;
	}
}