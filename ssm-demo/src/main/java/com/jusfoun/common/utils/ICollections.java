package com.jusfoun.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	public static boolean hasData(Collection<?> collection) {
		return collection != null && !collection.isEmpty() && collection.size() > 0;
	}

	public static boolean hasNoData(Collection<?> collection) {
		return !hasData(collection);
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
		List<Long> longs = null;
		if (StringUtils.isNotEmpty(str)) {
			String[] split = str.split(separator);
			if (split.length > 0) {
				longs = new ArrayList<Long>();
				for (String s : split) {
					if (StringUtils.isNotEmpty(s)) {
						longs.add(Long.valueOf(s.trim()));
					}
				}
			}
		}
		return longs;
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
		List<Integer> ints = null;
		if (StringUtils.isNotEmpty(str)) {
			String[] split = str.split(separator);
			if (split.length > 0) {
				ints = new ArrayList<Integer>();
				for (String s : split) {
					if (StringUtils.isNotEmpty(s)) {
						ints.add(Integer.valueOf(s.trim()));
					}
				}
			}
		}
		return ints;
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
		List<String> strings = null;
		if (StringUtils.isNotEmpty(str)) {
			String[] split = str.split(separator);
			if (split.length > 0) {
				strings = new ArrayList<String>();
				for (String s : split) {
					if (StringUtils.isNotEmpty(s)) {
						strings.add(s.trim());
					}
				}
			}
		}
		return strings;
	}
}
