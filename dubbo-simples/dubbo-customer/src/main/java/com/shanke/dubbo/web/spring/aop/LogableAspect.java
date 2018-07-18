package com.shanke.dubbo.web.spring.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.shanke.dubbo.comm.annotation.Logable;
import com.shanke.dubbo.comm.annotation.Logable.LevelType;

/**
 * 描述 : 方法调用信息日志输出切面. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2017-3-9 下午2:21:05
 * @version 1.0
 */
@Aspect
@Component
public class LogableAspect extends AbstractAspect {

	protected static Logger logger = LoggerFactory
			.getLogger(LogableAspect.class);

	@Pointcut("execution(* com.shanke.dubbo.web.controller..*(..)) and @annotation(com.shanke.dubbo.comm.annotation.Logable)")
	public void joinPointExpression() {
	}

	@Around(value = "joinPointExpression()")
	public Object aroundMethod(ProceedingJoinPoint pjd) throws Exception {
		Object result = null;
		String message = "";

		// 类
		Class<? extends Object> clazz = pjd.getTarget().getClass();
		// 方法
		Signature signature = pjd.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();
		// 参数
		Object[] args = pjd.getArgs();

		Logable l = null;
		boolean logable = false;
		LevelType level = null;
		try {
			boolean ap1 = clazz.isAnnotationPresent(Logable.class);
			boolean ap2 = method.isAnnotationPresent(Logable.class);

			if (ap2) {
				l = method.getAnnotation(Logable.class);
			} else if (ap1) {
				l = clazz.getAnnotation(Logable.class);
			}

			if (l != null) {
				logable = l.abled();
				level = l.level();
				if (logable) {
					message = "The method " + signature + " begins with "
							+ Arrays.asList(args);
					log(level, message);
					result = pjd.proceed();
					message = "The method " + signature + " ends with result "
							+ result;
					log(level, message);
				} else {
					return pjd.proceed(args);
				}
			} else {
				return pjd.proceed(args);
			}
		} catch (Throwable e) {
			if (logable) {
				message = "The method " + signature + " occurs exception:" + e;
				logger.error(message);
			}
			throw new Exception(e);
		}
		return result;
	}

	// 根据指定级别输出对应的日志信息
	private void log(LevelType level, String message) {
		switch (level) {
		case INFO:
			logger.info(message);
			break;
		case WARN:
			logger.warn(message);
			break;
		case ERROR:
			logger.error(message);
			break;
		default:
			logger.debug(message);
			break;
		}
	}
}
