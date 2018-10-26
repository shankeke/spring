package com.jusfoun.common.base.extend.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jusfoun.common.base.extend.adapter.EntityPreprocessAdapter;
import com.jusfoun.common.base.extend.annotation.PreInsert;
import com.jusfoun.common.base.extend.annotation.PreUpdate;
import com.jusfoun.entity.SysUser;
import com.jusfoun.security.util.SecurityUtils;
import com.jusfoun.service.SysUserService;

/**
 * 说明：实体对象预处理切面. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月30日 上午11:31:24
 */
@Aspect
@Component
public class EntityPreprocessAspect {

	private static final Logger log = LoggerFactory.getLogger(EntityPreprocessAspect.class);

	@Autowired
	private EntityPreprocessAdapter preprocessAdapter;

	@Autowired
	private SysUserService sysUserService;

	/**
	 * 说明： 默认拦截@Preprocess注解的方法. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月2日 下午8:04:36
	 */
	@Pointcut("@annotation(com.jusfoun.common.base.extend.annotation.Preprocess)")
	public void joinPointExpression() {
	}

	@Around(value = "joinPointExpression()")
	public Object aroundMethod(ProceedingJoinPoint pjd) throws Throwable {
		// 获取当前系统登录用户信息
		Long userId = null;
		String realName = null;
		try {
			SysUser sysUser = sysUserService.selectByUsername(SecurityUtils.getCurrentUserUsername());
			if (sysUser != null) {
				userId = sysUser.getId();
				realName = sysUser.getRealName();
			}
		} catch (Exception e) {
			log.error("未获取到当前登录用户信息，使用默认设置", e);
			userId = 1L;
			realName = "admin";
		}

		try {
			// 方法
			MethodSignature methodSignature = (MethodSignature) pjd.getSignature();
			Method method = methodSignature.getMethod();
			Object[] args = pjd.getArgs();

			Parameter[] parameters = method.getParameters();
			Annotation annotation = null;
			Parameter parameter = null;
			if (parameters != null && parameters.length > 0) {
				for (int i = 0; i < parameters.length; i++) {
					parameter = parameters[i];
					annotation = parameter.getAnnotation(PreInsert.class);
					if (annotation != null) {
						preprocessAdapter.preprocess(annotation, args[i], userId, realName);
					}
					annotation = parameter.getAnnotation(PreUpdate.class);
					if (annotation != null) {
						preprocessAdapter.preprocess(annotation, args[i], userId, realName);
					}
				}
			}
			return pjd.proceed(args);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
