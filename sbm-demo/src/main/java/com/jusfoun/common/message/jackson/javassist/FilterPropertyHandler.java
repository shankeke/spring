package com.jusfoun.common.message.jackson.javassist;

import java.lang.reflect.Method;

public interface FilterPropertyHandler {

	/**
	 * 描述 :过滤属性方法. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月11日 上午11:03:12
	 * @param method
	 *            执行的方法
	 * @param object
	 *            过滤的对象
	 * @return
	 */
	public Object filterProperties(Method method, Object object);
}
