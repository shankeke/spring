package com.jusfoun.common.message.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明：json字段过滤器，指定的java类型的对象在序列化为json串的时候实现字段的排除或者包含关系的指定，达到定制化的目的. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月7日 下午2:33:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(JsonBodys.class)
public @interface JsonBody {

	/**
	 * 说明： 需要过滤字段的类型. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月18日 上午9:55:10
	 * @return 过滤类型
	 */
	Class<?> type();

	/**
	 * 说明： 需要序列换的字段名数组. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月18日 上午9:55:58
	 * @return 序列化的字段名数组
	 */
	String[] includes() default {};

	/**
	 * 说明： 需要忽略掉的字段名数组 <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月18日 上午9:56:49
	 * @return 忽略掉的字段名数组
	 */
	String[] excludes() default {};

}
