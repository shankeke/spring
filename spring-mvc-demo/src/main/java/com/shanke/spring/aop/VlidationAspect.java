package com.shanke.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class VlidationAspect extends BaseAspect {

	@Before("joinPointExpression()")
	public void validateArgs(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		String name = signature.getName();
		logger.debug("【VlidationAspect】validate: target="
				+ joinPoint.getTarget() + ", " + " method=" + name + ", args="
				+ Arrays.asList(joinPoint.getArgs()));
	}
}
