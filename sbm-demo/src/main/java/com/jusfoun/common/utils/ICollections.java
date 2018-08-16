package com.jusfoun.common.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * 描述 :集合工具类. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月16日 上午11:32:23
 */
public class ICollections {

	/**
	 * 描述 : 判断集合中是否有数据. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 上午11:32:37
	 * @param list
	 *            判断的集合
	 * @return 集合为空返回false，否则返回true
	 */
	public static boolean hasElements(Collection<?> collection) {
		return collection != null && !collection.isEmpty() && collection.size() > 0;
	}

	public static boolean hasNoElements(Collection<?> collection) {
		return !hasElements(collection);
	}

	/**
	 * 描述: 将字符串装换成指定类型的数据集合. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年8月16日 下午5:52:45
	 * @param str
	 *            需要转化的字符串
	 * @param separator
	 *            分隔符
	 * @param function
	 *            转化函数
	 * @return 转化结果
	 */
	public static <T> List<T> strToList(String str, String separator, Function<String, T> function) {
		if (StringUtils.isNotEmpty(str)) {
			String[] arr = str.split(separator);
			if (arr.length > 0) {
				return Arrays.stream(arr).map(t -> function.apply(t)).collect(Collectors.toList());
			}
		}
		return null;
	}

	/**
	 * 描述 :字符串转化为指定类型的集合>. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 上午11:34:01
	 * @param str
	 *            处理字符串
	 * @param separator
	 *            分隔符
	 * @return Long类型数据集合
	 */
	public static List<Long> strToLongList(String str, String separator) {
		return strToList(str, separator, Long::valueOf);
	}

	/**
	 * 描述 : 字符串转化为指定类型的集合. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 上午11:34:47
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return Integer类型数据集合
	 */
	public static List<Integer> strToIntegerList(String str, String separator) {
		return strToList(str, separator, Integer::valueOf);
	}

	/**
	 * 描述 : 字符串转化为指定类型的集合. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 上午11:34:47
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return String类型数据集合
	 */
	public static List<String> strToStringList(String str, String separator) {
		return strToList(str, separator, String::valueOf);
	}

	public static void main(String[] args) {
		String str = "1,2,3,4,5";
		List<Integer> strToList = strToList(str, ",", Integer::valueOf);
		System.out.println(strToList);
	}
}
