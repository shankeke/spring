package com.jusfoun.common.jackson.utils;

import java.lang.reflect.Field;

import org.apache.ibatis.javassist.util.proxy.ProxyFactory;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

/**
 * 描述 : 代理工具类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月7日 下午8:05:12
 */
public class ProxyTargetUtils {

	/**
	 * 描述 :获取代理对象的正式类型. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月7日 下午8:25:34
	 * @param proxy
	 *            代理对象
	 * @return 代理对象的真实类型
	 * @throws Exception
	 */
	public static Class<?> getTargetClass(Object proxy) throws Exception {
		if (AopUtils.isAopProxy(proxy)) {
			return AopUtils.getTargetClass(proxy);
		} else if (ProxyFactory.isProxyClass(proxy.getClass())) {
			return proxy.getClass().getSuperclass();
		} else {
			return proxy.getClass();
		}
	}

	/**
	 * 描述 :获取代理对象的原来的类型对象. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月8日 上午10:32:41
	 * @param proxy
	 *            代理对象
	 * @return
	 * @throws Exception
	 */
	public static Object getTarget(Object proxy) throws Exception {
		if (!AopUtils.isAopProxy(proxy)) {
			return proxy;
		}
		if (AopUtils.isJdkDynamicProxy(proxy)) {
			return getJdkDynamicProxyTargetObject(proxy);
		} else {
			return getCglibProxyTargetObject(proxy);
		}
	}

	/**
	 * 描述 : 获取CGLIB代理的对象真实对象. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月8日 上午10:33:37
	 * @param proxy
	 *            代理对象
	 * @return 真实类型对象
	 * @throws Exception
	 */
	private static Object getCglibProxyTargetObject(Object proxy) throws Exception {
		Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
		h.setAccessible(true);
		Object dynamicAdvisedInterceptor = h.get(proxy);

		Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
		advised.setAccessible(true);

		Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();

		return target;
	}

	/**
	 * 描述 : 获取JDK动态代理的真实对象. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2017年12月8日 上午10:34:38
	 * @param proxy
	 *            代理对象
	 * @return 真实对象
	 * @throws Exception
	 */
	private static Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
		Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
		h.setAccessible(true);
		AopProxy aopProxy = (AopProxy) h.get(proxy);

		Field advised = aopProxy.getClass().getDeclaredField("advised");
		advised.setAccessible(true);

		Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();

		return target;
	}
}
