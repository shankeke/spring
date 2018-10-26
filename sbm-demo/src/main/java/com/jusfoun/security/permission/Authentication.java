package com.jusfoun.security.permission;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明：后台鉴权注解，用于标示某一个请求是否需要进行鉴权处理，如果需要则通过aop中实现面向切面的对用户访问权限进行拦截处理。<br>
 * 该注解适用于Controller层的方法上，并配置是否开启鉴权和鉴权路径，当请求的路径与配置路径匹配时则放行，否则不放行 。<br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月2日 上午9:03:51
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authentication {

	/**
	 * 说明：是否开启日志，默认开启，需要关闭设为false即可. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月2日 上午10:47:12
	 * @return 是否开启访问鉴权配置，开启-true，关闭-false
	 */
	boolean enable() default true;

	/**
	 * 说明：权限标识. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月2日 上午10:48:19
	 * @return 对应的系统权限值
	 */
	String value() default "";

	/**
	 * 说明：方法功能说明. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月2日 上午10:48:53
	 * @return 权限的说明信息
	 */
	String desc() default "";

}