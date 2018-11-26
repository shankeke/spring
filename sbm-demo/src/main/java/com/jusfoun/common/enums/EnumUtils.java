package com.jusfoun.common.enums;

import java.util.Map;

import com.beust.jcommander.internal.Maps;
import com.jusfoun.common.enums.valuable.ValuableAndComparableAndLabelable;

/**
 * 说明： 枚举工具类. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年11月22日 下午12:38:16
 */
public class EnumUtils {

	/**
	 * 说明：获取枚举常量值和标签值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年11月22日 下午1:28:16
	 * @param values
	 *            枚举列表
	 * @return 枚举常量值和标签值映射
	 */
	public static <V extends Enum<V> & ValuableAndComparableAndLabelable<?>> Map<Object, String> values(V[] values) {
		Map<Object, String> map = Maps.newHashMap();
		for (V t : values) {
			map.put(t.getValue(), t.getLabel());
		}
		return map;
	}

	/**
	 * 说明：获取枚举常量值和标签值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年11月22日 下午1:28:16
	 * @param clazz
	 *            枚举类
	 * @return 枚举常量值和标签值映射
	 */
	public static <T> Map<Object, String> values(Class<? extends ValuableAndComparableAndLabelable<?>> clazz) {
		Map<Object, String> map = Maps.newHashMap();
		ValuableAndComparableAndLabelable<?>[] enumConstants = clazz.getEnumConstants();
		for (ValuableAndComparableAndLabelable<?> t : enumConstants) {
			map.put(t.getValue(), t.getLabel());
		}
		return map;
	}
}
