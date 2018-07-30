package com.jusfoun.common.base.extend.preprocessor;

import java.lang.annotation.Annotation;

import com.jusfoun.common.base.extend.entity.BaseEntity;

/**
 * 描述:继承<code>BaseEntity</code>实体对象抽象预处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月30日 上午10:31:09
 */
public abstract class AbstractBaseEntityPreprocessor implements BaseEntityPreprocessor {

	@Override
	public boolean supports(Annotation annotation, Class<?> clazz) {
		return annotationClass().isInstance(annotation) && BaseEntity.class.isAssignableFrom(clazz);
	}

	/**
	 * 描述 :获取注解类型. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月30日 下午7:44:05
	 * @return 注解类型
	 */
	public abstract Class<? extends Annotation> annotationClass();

}
