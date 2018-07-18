package com.shanke.common.conf;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述 : 通用的反射工具类. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author shanke
 * @date 2015年11月29日 下午9:38:26
 */
public class Reflection {

	private static final Class<?>[] EMPTY_ARRAY = new Class[] {};

	/**
	 * 缓存每个类的构造器，标记这些类是他们不被垃圾收集直到ReflectionUtils能够被收集
	 */
	private static final Map<Class<?>, Constructor<?>> CONSTRUCTOR_CACHE = new ConcurrentHashMap<Class<?>, Constructor<?>>();

	/**
	 * <pre>
	 * Description: 
	 *       类工厂方法，通过类名称返回实例
	 * </pre>
	 * 
	 * @author shanke
	 * @date 2015年11月29日 上午11:24:19
	 * @param type
	 * @return
	 */
	public static Object newInstance(String className) {
		Object obj = null;
		try {
			obj = Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * <pre>
	 * Description: 
	 *        从给定的类创建一个新实例
	 * </pre>
	 * 
	 * @author shanke
	 * @date 2015年11月29日 上午11:37:55
	 * @param theClass
	 *            需要创建实例的类
	 * @return 新实例
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<T> theClass) {
		T result = null;
		try {
			Constructor<T> meth = (Constructor<T>) CONSTRUCTOR_CACHE
					.get(theClass);
			if (meth == null) {
				meth = theClass.getDeclaredConstructor(EMPTY_ARRAY);
				meth.setAccessible(true);
				CONSTRUCTOR_CACHE.put(theClass, meth);
			}
			result = meth.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * 返回给定类{@link Class} 的正确类型
	 * 
	 * @param o
	 *            <code>Class</code>指定的正确类型的对象
	 * 
	 * @return 给定类<code>Class</code> 的正确类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClass(T o) {
		return (Class<T>) o.getClass();
	}

	/**
	 * <pre>
	 * Description: 
	 *        清空缓存中的构造器
	 * </pre>
	 * 
	 * @author shanke
	 * @date 2015年11月29日 上午11:30:01
	 */
	static void clearCache() {
		CONSTRUCTOR_CACHE.clear();
	}

	/**
	 * <pre>
	 * Description: 
	 *        获取缓存中构造器的大小
	 * </pre>
	 * 
	 * @author shanke
	 * @date 2015年11月29日 上午11:29:23
	 * @return 缓存大小
	 */
	static int getCacheSize() {
		return CONSTRUCTOR_CACHE.size();
	}

	/**
	 * <pre>
	 * Description: 
	 *         获取一个类中所有的公告的属性包含从父类中继承的
	 * </pre>
	 * 
	 * @author shanke
	 * @date 2015年11月29日 上午11:31:37
	 * @param clazz
	 *            类对象
	 * @return 属性集合
	 */
	public static List<Field> getDeclaredFieldsIncludingInherited(Class<?> clazz) {
		List<Field> fields = new ArrayList<Field>();
		while (clazz != null) {
			for (Field field : clazz.getDeclaredFields()) {
				fields.add(field);
			}
			clazz = clazz.getSuperclass();
		}
		return fields;
	}

	/**
	 * <pre>
	 * Description: 
	 *       获取一个类中所有的公告的方法包含从父类中继承的
	 * </pre>
	 * 
	 * @author shanke
	 * @date 2015年11月29日 上午11:34:45
	 * @param clazz
	 *            类对象
	 * @return 方法集合
	 */
	public static List<Method> getDeclaredMethodsIncludingInherited(
			Class<?> clazz) {
		List<Method> methods = new ArrayList<Method>();
		while (clazz != null) {
			for (Method method : clazz.getDeclaredMethods()) {
				methods.add(method);
			}
			clazz = clazz.getSuperclass();
		}
		return methods;
	}
}
