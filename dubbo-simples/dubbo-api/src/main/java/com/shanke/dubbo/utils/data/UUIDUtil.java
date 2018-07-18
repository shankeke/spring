package com.shanke.dubbo.utils.data;

import java.util.UUID;

/**
 * 描述 : UUID工具类 . <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016年8月1日 下午8:43:54
 */
public class UUIDUtil {
	/**
	 * 描述 : 获取生成的uuid . <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年8月1日 下午8:44:05
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
