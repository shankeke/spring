package com.jusfoun.common.base.extend.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.common.base.extend.adapter.EntityPreprocessAdapter;
import com.jusfoun.common.base.extend.annotation.PreInsert;
import com.jusfoun.common.base.extend.annotation.PreUpdate;
import com.jusfoun.common.base.extend.annotation.Preprocess;
import com.jusfoun.entity.SysUser;
import com.jusfoun.security.util.SecurityUtils;
import com.jusfoun.service.SysUserService;

/**
 * 描述:实体对象预处理切面. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月30日 上午11:31:24
 */
public class EntityPreprocessAspect {

	private static final Logger log = LoggerFactory.getLogger(EntityPreprocessAspect.class);

	@Autowired
	private EntityPreprocessAdapter preprocessAdapter;

	@Autowired
	private SysUserService sysUserService;

	/**
	 * 描述 : 默认拦截@Preprocess注解的方法. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年11月2日 下午8:04:36
	 */
	// @Pointcut("@annotation(com.jusfoun.common.base.extend.annotation.Preprocess)")
	@Pointcut("(execution(* com.jusfoun.service..*(..)) || execution(* com.jusfoun.common.base.service..*(..))) && @annotation(com.jusfoun.common.base.extend.annotation.Preprocess)")
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
			log.error("未获取到当前登录用户信息，使用默认设置");
			userId = 1L;
			realName = "admin";
		}

		Object[] args = null;
		try {
			// 方法
			MethodSignature methodSignature = (MethodSignature) pjd.getSignature();
			Method method = methodSignature.getMethod();

			/*
			 * Parameter[] parameters = method.getParameters(); for (int j = 0;
			 * j < parameters.length; j++) { Parameter parameter =
			 * parameters[j]; Annotation[] annotations =
			 * parameter.getDeclaredAnnotations(); for (int k = 0; k <
			 * annotations.length; k++) { Annotation annotation =
			 * annotations[k]; } }
			 */

			Preprocess preprocess = method.getAnnotation(Preprocess.class);
			args = pjd.getArgs();

			int index = 0;
			int length = args.length;
			Object entity = null;
			if (args != null && length > 0) {
				// 插入预处理
				PreInsert[] inserts = preprocess.inserts();
				if (inserts != null && inserts.length > 0) {
					for (PreInsert preInsert : inserts) {
						index = preInsert.index();
						if (length >= (index + 1)) {
							entity = args[index];
							entity = preprocessAdapter.preprocess(preInsert, userId, realName, entity);
							args[preInsert.index()] = entity;
						}
					}
				}

				// 更新预处理
				PreUpdate[] updates = preprocess.updates();
				if (inserts != null && inserts.length > 0) {
					for (PreUpdate preUpdate : updates) {
						index = preUpdate.index();
						if (length >= (index + 1)) {
							entity = args[preUpdate.index()];
							entity = preprocessAdapter.preprocess(preUpdate, userId, realName, entity);
							args[preUpdate.index()] = entity;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return pjd.proceed(args);
	}
}
