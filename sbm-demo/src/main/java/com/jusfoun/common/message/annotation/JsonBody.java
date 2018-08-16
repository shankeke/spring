package com.jusfoun.common.message.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述:json格式数据声明注解，使用该注解方法返回数据使用<code>FilterFieldsJsonReturnHandler</code>处理，配合<code>JsonFilter</code>
 * 实现对将要按照json格式输出的实体进行字段过滤，以达到字段定制的目的. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月7日 下午2:29:56
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonBody {

	/**
	 * 描述:支持同时过滤多个数据类型的字段. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月18日 上午9:58:01
	 * @return 过滤配置数组
	 */
	Json[] value() default {};

}