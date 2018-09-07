package com.jusfoun.common.base.extend.adapter;

import java.lang.annotation.Annotation;

import com.jusfoun.common.base.extend.preprocessor.EntityPreprocessor;

/**
 * 描述:实体对象预处理器适配器定义. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月30日 上午10:22:13
 */
public interface EntityPreprocessAdapter {

	/**
	 * 描述:预处理方法. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月30日 上午10:49:53
	 * @param userId
	 *            当前用户ID
	 * @param realName
	 *            当前用户姓名
	 * @param annotation
	 *            预处理参数上的注解
	 * @param obj
	 *            实体对象（可以是支持的实体集合）
	 */
	void preprocess(Long userId, String realName, Annotation annotation, Object obj);

	/**
	 * 描述 :添加预处理器. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月30日 下午7:33:05
	 */
	EntityPreprocessAdapter add(EntityPreprocessor preprocessor);

	/**
	 * 描述:获取预处理器. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年8月2日 上午9:58:30
	 * @param annotation
	 *            注解实例
	 * @param clazz
	 *            处理对象实例的类型
	 * @return 合适的预处理器
	 */
	EntityPreprocessor get(Annotation annotation, Class<? extends Object> clazz);

}
