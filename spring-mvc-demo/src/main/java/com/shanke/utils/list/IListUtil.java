package com.shanke.utils.list;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 描述 : 集合工具类. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016年8月5日 下午9:20:29
 */
public class IListUtil {
	/**
	 * <pre>
	 * Description:
	 *      判断集合中是否有数据
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-5-28
	 * @param list
	 *            集合数据
	 * @return true 有数据，false 无数据
	 */
	public static boolean hasData(List<?> list) {
		return list != null && !list.isEmpty() && list.size() > 0;
	}

	/**
	 * <pre>
	 * Description:
	 *        讲字符串转化为指定类型的集合
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-6-4
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static List<Long> strToLongList(String str, String separator) {
		List<Long> longs = null;
		if (!StringUtils.isEmpty(str)) {
			String[] split = str.split(separator);
			if (split.length > 0) {
				longs = new ArrayList<Long>();
				for (String s : split) {
					if (!StringUtils.isEmpty(s) && s.trim().length() > 0) {
						longs.add(Long.valueOf(s.trim()));
					}
				}
			}
		}
		return longs;
	}

	/**
	 * <pre>
	 * Description:
	 *        讲字符串转化为指定类型的集合
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-6-4
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static List<Integer> strToIntegerList(String str, String separator) {
		List<Integer> ints = null;
		if (!StringUtils.isEmpty(str)) {
			String[] split = str.split(separator);
			if (split.length > 0) {
				ints = new ArrayList<Integer>();
				for (String s : split) {
					if (!StringUtils.isEmpty(s) && s.trim().length() > 0) {
						ints.add(Integer.valueOf(s.trim()));
					}
				}
			}
		}
		return ints;
	}

	/**
	 * <pre>
	 * Description:
	 *        讲字符串转化为指定类型的集合
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-6-4
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return
	 */
	public static List<String> strToStringList(String str, String separator) {
		List<String> strings = null;
		if (!StringUtils.isEmpty(str)) {
			String[] split = str.split(separator);
			if (split.length > 0) {
				strings = new ArrayList<String>();
				for (String s : split) {
					if (!StringUtils.isEmpty(s) && s.trim().length() > 0) {
						strings.add(s.trim());
					}
				}
			}
		}
		return strings;
	}
}
