package com.jusfoun.common.base.extend.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Preprocess {
	/**
	 * 描述:插入预处理注解数组. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月30日 上午11:45:58
	 * @return 注解数组
	 */
	PreInsert[] inserts() default {};

	/**
	 * 描述:更新预处理注解数组. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月30日 上午11:45:58
	 * @return 注解数组
	 */
	PreUpdate[] updates() default {};
}
