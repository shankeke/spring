package com.jusfoun.common.base.extend.preprocessor;

import java.lang.annotation.Annotation;

/**
 * 描述:实体对象预处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月30日 上午10:14:40
 */
public interface BaseEntityPreprocessor {

	/**
	 * 描述:预处理方法. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月30日 上午10:49:53
	 * @param userId
	 *            用户ID
	 * @param realName
	 *            用户姓名
	 * @param obj
	 *            实体参数
	 * @return 预处理之后的对象
	 */
	Object process(Long userId, String realName, Object obj);

	/**
	 * 描述:是否支持. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月30日 上午10:59:34
	 * @param annotation
	 *            注解对象
	 * @param clazz
	 *            实体类型
	 * @return 是否支持
	 */
	boolean supports(Annotation annotation, Class<?> clazz);
}
