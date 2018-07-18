package com.shanke.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述 :<br>
 * 日志切面注解，使用在类上面和方法上面:<br>
 * 如果用在类上面，该类下面的所有方法会被切面拦截（ 如果定义了切面的话）；<br>
 * 如果在方法上面则只对该方法生效，方法上的优先级高于类型上的注解；<br>
 * 切入点根据相应属性判断是否输出日志信息，如果需要输出根据级别输出日志，该注解主要是方便查看指定的方法输入参数和返回结果。 <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2017-3-6 下午2:48:17
 * @version 1.0
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logable {

	// 是否开启日志，默认开启，需要关闭设为false即可。
	boolean enable() default true;

	// 日志级别，默认debug
	LevelType level() default LevelType.DEBUG;

	public enum LevelType {
		OFF, DEBUG, INFO, WARN, ERROR, FATAL, ALL;
	}
}
