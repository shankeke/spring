package com.shanke.utils.data;

import java.lang.reflect.Field;

import javax.persistence.Id;

/**
 * 描述 : 反射相关方法工具类 . <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016年8月1日 下午8:44:39
 */
public class ReflectHelper {

	/**
	 * 描述 : <方法描述>. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016年8月1日 下午8:45:01
	 * @param clazz
	 * @return
	 */
	public static Field getIdField(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Field item = null;
		for (Field field : fields) {
			// 获取实体类中标识@Id的字段
			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				field.setAccessible(true);
				item = field;
				break;
			}
		}
		if (item == null) {
			Class<?> superclass = clazz.getSuperclass();
			if (superclass != null) {
				item = getIdField(superclass);
			}
		}
		return item;
	}
}
