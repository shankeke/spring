package com.shanke.spring.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseAspect {
	protected static Logger logger = LoggerFactory.getLogger(BaseAspect.class);

	/**
	 * 定义一个方法, 用于声明切入点表达式. 一般地, 该方法中再不需要添入其他的代码. 使用 @Pointcut 来声明切入点表达式.
	 * 后面的其他通知直接使用方法名来引用当前的切入点表达式.
	 */
	@Pointcut("execution(* com.shanke.web.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void joinPointExpression() {
	}
}
