package com.shanke.shiro.utils;

import java.io.Serializable;

import org.apache.shiro.util.SimpleByteSource;

/**
 * 描述 : 继承SimpleByteSource，实现序列化接口. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-8-29 下午6:49:20
 */
public class MySimpleByteSource extends SimpleByteSource implements
		Serializable {
	private static final long serialVersionUID = -1644585188636422388L;

	public MySimpleByteSource(byte[] bytes) {
		super(bytes);
	}

	public MySimpleByteSource(String salt) {
		super(salt);
	}
}
