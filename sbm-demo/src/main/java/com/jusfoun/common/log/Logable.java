package com.jusfoun.common.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明：<br>
 * 访问日志注解，使用在类和方法上:<br>
 * 如果用在类上使用，该类下面的所有方法会被拦截（ 如果定义了切面的话）；<br>
 * 如果在方法上面则只对该方法生效，方法上的优先级高于类型上的注解；<br>
 * 切入点根据相应属性判断是否输出日志信息，如果需要输出根据级别输出日志，该注解主要是方便查看指定的方法输入参数和返回结果。 <br>
 * <p>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:34:56
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logable {

	/**
	 * 说明：是否记录日志，默认true，需关闭设为false. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月18日 上午9:39:22
	 * @return 是否记录日志
	 */
	boolean enable() default true;

	/**
	 * 说明：功能名称<br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月18日 上午9:41:53
	 * @return 功能名称
	 */
	String value() default "";

	/**
	 * 说明： 自定义访问地址，如：【/sysuser/save】对应【系统管理>用户管理>用户添加】. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月18日 上午9:41:06
	 * @return 访问地址
	 */
	String path() default "";

	/**
	 * 说明：日志内容（支持spel表达式）<br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月18日 上午9:41:53
	 * @return 日志内容
	 */
	String message() default "";

	/**
	 * 说明： 日志级别，默认DEBUG. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月18日 上午9:40:26
	 * @return 日志级别
	 */
	LevelType level() default LevelType.DEBUG;

	/**
	 * 说明： 日志等级. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月18日 上午9:42:26
	 */
	public enum LevelType {
		OFF, TRACE, DEBUG, INFO, WARN, ERROR, FATAL, ALL;
	}
}
