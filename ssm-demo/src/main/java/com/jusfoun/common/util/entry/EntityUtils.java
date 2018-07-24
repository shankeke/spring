package com.jusfoun.common.util.entry;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class EntityUtils {

	/**************************** 这一部分用于处理基本数据拆包常见处理类型 ******************************/
	/**
	 * 描述:为空则返回默认值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月21日 下午1:54:09
	 * @param clazz
	 *            数据类型
	 * @param src
	 *            元数据
	 * @param def
	 *            默认值
	 * @return src不为空返回src，否则返回def
	 */
	public static <T> T getDefaultIfNull(Class<T> clazz, T src, T def) {
		return null == src ? def : src;
	}

	/**
	 * 描述:为空则返回默认值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月21日 下午1:54:09
	 * @param src
	 *            元数据
	 * @param def
	 *            默认值
	 * @return src不为空返回src，否则返回def
	 */
	public static Byte getDefaultIfNull(Byte src, Byte def) {
		return getDefaultIfNull(Byte.class, src, def);
	}

	/**
	 * 描述:为空则返回默认值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月21日 下午1:54:09
	 * @param src
	 *            元数据
	 * @param def
	 *            默认值
	 * @return src不为空返回src，否则返回def
	 */
	public static Short getDefaultIfNull(Short src, Short def) {
		return getDefaultIfNull(Short.class, src, def);
	}

	/**
	 * 描述:为空则返回默认值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月21日 下午1:54:09
	 * @param src
	 *            元数据
	 * @param def
	 *            默认值
	 * @return src不为空返回src，否则返回def
	 */
	public static Integer getDefaultIfNull(Integer src, Integer def) {
		return getDefaultIfNull(Integer.class, src, def);
	}

	/**
	 * 描述:为空返回默认值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月21日 下午1:54:09
	 * @param src
	 *            元数据
	 * @param def
	 *            默认值
	 * @return src不为空返回src，否则返回def
	 */
	public static Long getDefaultIfNull(Long src, Long def) {
		return getDefaultIfNull(Long.class, src, def);
	}

	/**
	 * 描述:为空返回默认值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月21日 下午1:54:09
	 * @param src
	 *            元数据
	 * @param def
	 *            默认值
	 * @return src不为空返回src，否则返回def
	 */
	public static Float getDefaultIfNull(Float src, Float def) {
		return getDefaultIfNull(Float.class, src, def);
	}

	/**
	 * 描述:为空返回默认值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月21日 下午1:54:09
	 * @param src
	 *            元数据
	 * @param def
	 *            默认值
	 * @return src不为空返回src，否则返回def
	 */
	public static Double getDefaultIfNull(Double src, Double def) {
		return getDefaultIfNull(Double.class, src, def);
	}

	/**
	 * 描述:为空则返回默认值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月21日 下午1:54:09
	 * @param src
	 *            元数据
	 * @param def
	 *            默认值
	 * @return src不为空返回src，否则返回def
	 */
	public static Boolean getDefaultIfNull(Boolean src, Boolean def) {
		return getDefaultIfNull(Boolean.class, src, def);
	}

	/**
	 * 描述:为空则返回默认值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年3月21日 下午1:54:09
	 * @param src
	 *            元数据
	 * @param def
	 *            默认值
	 * @return src不为空返回src，否则返回def
	 */
	public static Character getDefaultIfNull(Character src, Character def) {
		return getDefaultIfNull(Character.class, src, def);
	}
	/**************************** 这一部分用于处理bean与map对象互转的功能 ******************************/
	// Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	public static void transMap2Bean(Map<String, Object> map, Object obj) {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			String key = null;
			Object value = null;
			Method setter = null;
			for (PropertyDescriptor property : propertyDescriptors) {
				key = property.getName();
				if (map.containsKey(key)) {
					value = map.get(key);
					// 得到property对应的setter方法
					setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					if (getter != null) {
						Object value = getter.invoke(obj);
						map.put(key, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static Object mapToBean(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;
		Object obj = beanClass.newInstance();
		org.apache.commons.beanutils.BeanUtils.populate(obj, map);
		return obj;
	}

	public static Map<?, ?> beanToMap(Object obj) {
		if (obj == null)
			return null;
		return new org.apache.commons.beanutils.BeanMap(obj);
	}

	public static Object map2Bean(Map<String, Object> map, Class<?> beanClass) throws Exception {
		if (map == null)
			return null;

		Object obj = beanClass.newInstance();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
				continue;
			}
			field.setAccessible(true);
			field.set(obj, map.get(field.getName()));
		}
		return obj;
	}

	public static Map<String, Object> bean2Map(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}
}
