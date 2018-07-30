package com.jusfoun.common.base.extend.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreUpdateList {
	/**
	 * 描述:参数坐标，如果只有一个参数不用标注即可. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月30日 上午11:42:13
	 * @return 默认第一个参数
	 */
	int index() default 0;
}
