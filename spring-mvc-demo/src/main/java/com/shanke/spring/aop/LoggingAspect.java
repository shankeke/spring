package com.shanke.spring.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 可以使用 @Order 注解指定切面的优先级, 值越小优先级越高
 */
@Order(2)
@Aspect
@Component
public class LoggingAspect extends BaseAspect {

	/**
	 * 在 com.atguigu.spring.aop.ArithmeticCalculator 接口的每一个实现类的每一个方法开始之前执行一段代码
	 */
	@Before("joinPointExpression()")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		logger.debug("【LoggingAspect】The method " + joinPoint.getTarget() + "."
				+ methodName + " begins with " + Arrays.asList(args));
	}

	/**
	 * 在方法执行之后执行的代码. 无论该方法是否出现异常
	 */
	@After("joinPointExpression()")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		logger.debug("【LoggingAspect】The method " + joinPoint.getTarget() + "."
				+ methodName + " ends");
	}

	/**
	 * 在方法法正常结束受执行的代码 返回通知是可以访问到方法的返回值的!
	 */
	@AfterReturning(value = "joinPointExpression()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		logger.debug("【LoggingAspect】The method " + joinPoint.getTarget() + "."
				+ methodName + " ends with " + result);
	}

	/**
	 * 在目标方法出现异常时会执行的代码. 可以访问到异常对象; 且可以指定在出现特定异常时在执行通知代码
	 */
	@AfterThrowing(value = "joinPointExpression()", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		String methodName = joinPoint.getSignature().getName();
		logger.debug("【LoggingAspect】The method " + joinPoint.getTarget() + "."
				+ methodName + " occurs excetion:" + e);
	}

	/**
	 * 环绕通知需要携带 ProceedingJoinPoint 类型的参数. 环绕通知类似于动态代理的全过程: ProceedingJoinPoint
	 * 类型的参数可以决定是否执行目标方法. 且环绕通知必须有返回值, 返回值即为目标方法的返回值
	 */

	@Around("execution(public int com.atguigu.spring.aop.ArithmeticCalculator.*(..))")
	public Object aroundMethod(ProceedingJoinPoint pjd) {
		Object result = null;
		String methodName = pjd.getSignature().getName();
		try { // 前置通知
			logger.debug("【LoggingAspect】The method " + methodName
					+ " begins with " + Arrays.asList(pjd.getArgs())); // 执行目标方法
			result = pjd.proceed(); // 返回通知
			logger.debug("【LoggingAspect】The method " + methodName
					+ " ends with " + result);
		} catch (Throwable e) { // 异常通知
			logger.debug("【LoggingAspect】The method " + methodName
					+ " occurs exception:" + e);
			throw new RuntimeException(e);
		} // 后置通知
		logger.debug("【LoggingAspect】The method " + methodName + " ends");

		return result;
	}
}
