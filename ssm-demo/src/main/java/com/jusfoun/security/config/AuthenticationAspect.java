package com.jusfoun.security.config;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.utils.list.IListUtil;
import com.jusfoun.security.exceptions.UnAuthorizedException;
import com.jusfoun.security.support.permission.annotation.Authentication;
import com.jusfoun.security.util.SecurityUtils;
import com.jusfoun.service.SysModuleService;

/**
 * 描述:权限访问控制处理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月10日 下午2:32:00
 */
@Aspect
@Component
public class AuthenticationAspect {
	protected static Logger logger = LoggerFactory.getLogger(AuthenticationAspect.class);

	@Autowired
	private SysModuleService sysModuleService;

	// @Pointcut("execution(* com.jusfoun.web.controller..*(..)) &&
	// @annotation(com.jusfoun.security.authorize.permission.annotation.Authentication)")
	@Pointcut("execution(* com.jusfoun.web.controller..*(..))")
	public void joinPointExpression() {
	}

	/**
	 * 描述 :定义前置通知，在执行请求方法之前就对当前用户的权限进行判断，如果有该权限则放行，否则抛出异常. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月2日 下午2:32:55
	 * @param joinpoint
	 *            切点
	 * @throws ServiceException
	 */
	@Before(value = "joinPointExpression()")
	public void beforeMethod(JoinPoint joinpoint) throws ServiceException {

		// 方法
		MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();
		Method method = methodSignature.getMethod();

		/*
		 * 拿到方法中注解，如果该方法存在Authentication注解则从中判断是否处于启用状态，如果启用则取它配置的权限值，
		 * 根据该权限值判断用户是否存在当前请求的权限
		 */
		Authentication auth = method.getAnnotation(Authentication.class);

		// 获取请求的权限值
		String authority = null;
		if (auth != null && auth.enable()) {
			// 获取方法注解中配置的权限值
			authority = auth.value();
		} else {
			// 从请求上下文中拿到当前request对象
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			if (request != null) {
				// 如果注解中没有配置权限值，则默认是请求路径
				if (StringUtils.isEmpty(authority)) {
					authority = request.getServletPath();
				}
			}
		}

		// 判断该请求是否需要判断权限
		Set<String> allAuthorities = sysModuleService.selectUrlByClientId(null);
		if (allAuthorities.contains(authority)) {
			// 获取当前用户的权限列表
			Collection<String> grantedAuthorities = SecurityUtils.getCurrentUserAuthorities();
			// 判断用户是否有该权限，如果没有该权限则抛出没有权限的异常，交给异常处理类处理
			if (!IListUtil.hasData(grantedAuthorities) || !grantedAuthorities.contains(authority)) {
				throw new UnAuthorizedException("未授权的请求", authority);
			}
		}
	}
}
