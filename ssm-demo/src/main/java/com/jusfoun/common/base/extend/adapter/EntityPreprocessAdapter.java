package com.jusfoun.common.base.extend.adapter;

import java.lang.annotation.Annotation;

import com.jusfoun.common.base.extend.preprocessor.BaseEntityPreprocessor;

/**
 * 描述:继承<code>BaseEntity</code>实体对象预处理器适配器接口. <br>
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
	 * @param annotation
	 *            参数上的注解
	 * @param userId
	 *            用户ID
	 * @param realName
	 *            用户姓名
	 * @param obj
	 *            实体对象或集合对象
	 */
	void preprocess(Annotation annotation, Long userId, String realName, Object obj);

	/**
	 * 描述 :添加预处理器. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月30日 下午7:33:05
	 */
	void add(BaseEntityPreprocessor preprocessor);

}
