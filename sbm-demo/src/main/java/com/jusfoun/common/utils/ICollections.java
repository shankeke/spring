package com.jusfoun.common.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * 说明：集合工具类. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月16日 上午11:32:23
 */
public class ICollections {

	/**
	 * 说明： 判断集合中是否有数据. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月16日 上午11:32:37
	 * @param collection
	 *            需要判断的集合
	 * @return 集合不为空返回true，否则返回false
	 */
	public static boolean hasElements(Collection<?> collection) {
		return collection != null && !collection.isEmpty() && collection.size() > 0;
	}

	/**
	 * 说明：判断集合中是否没有数据.. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年9月30日 下午2:24:03
	 * @param collection
	 *            需要判断的集合
	 * @return 集合为空返回true，否则返回false
	 */
	public static boolean hasNoElements(Collection<?> collection) {
		return !hasElements(collection);
	}

	/**
	 * 说明：集合转数组. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年8月29日 下午4:02:16
	 * @param collection
	 *            集合数据
	 * @return 数组
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Collection<T> collection) {
		return (T[]) collection.toArray();
	}

	/**
	 * 说明：将一种数据集合转化成另一种数据集合. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年8月29日 下午5:11:43
	 * @param collection
	 *            源集合
	 * @param convertor
	 *            转化器
	 * @return 转化结果集
	 */
	public static <T, R> List<R> convertToList(Collection<T> collection, Function<T, R> convertor) {
		if (hasElements(collection)) {
			return collection//
					.stream()//
					.map(t -> convertor.apply(t))//
					.collect(Collectors.toList());
		}
		return null;
	}

	/**
	 * 说明：将一种数据集合转化成另一种数据集合. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年8月29日 下午5:11:43
	 * @param collection
	 *            源集合
	 * @param convertor
	 *            转化器
	 * @return 转化结果集
	 */
	public static <T, R> Set<R> convertToSet(Collection<T> collection, Function<T, R> convertor) {
		if (hasElements(collection)) {
			return collection//
					.stream()//
					.map(t -> convertor.apply(t))//
					.collect(Collectors.toSet());
		}
		return null;
	}

	/**
	 * 说明： 将字符串装换成指定类型的数据集合. <br>
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
	public static <T> List<T> strToList(String str, String separator, Function<String, T> convertor) {
		if (StringUtils.isNotEmpty(str)) {
			return Arrays//
					.stream(StringUtils.split(str, separator))//
					.map(t -> convertor.apply(t))//
					.collect(Collectors.toList());
		}
		return null;
	}

	/**
	 * 说明：字符串转Byte数组. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年9月5日 上午9:43:44
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return Byte数组
	 */
	public static List<Byte> strToByteList(String str, String separator) {
		return strToList(str, separator, Byte::valueOf);
	}

	/**
	 * 说明：字符串转Short数组. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年9月5日 上午9:43:44
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return Short数组
	 */
	public static List<Short> strToShortList(String str, String separator) {
		return strToList(str, separator, Short::valueOf);
	}

	/**
	 * 说明：字符串转Integer数组. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年9月5日 上午9:43:44
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return Integer数组
	 */
	public static List<Integer> strToIntegerList(String str, String separator) {
		return strToList(str, separator, Integer::valueOf);
	}

	/**
	 * 说明：字符串转Long数组. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年9月5日 上午9:43:44
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return Long数组
	 */
	public static List<Long> strToLongList(String str, String separator) {
		return strToList(str, separator, Long::valueOf);
	}

	/**
	 * 说明：字符串转Float数组. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年9月5日 上午9:43:44
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return Float数组
	 */
	public static List<Float> strToFloatList(String str, String separator) {
		return strToList(str, separator, Float::valueOf);
	}

	/**
	 * 说明：字符串转Double数组. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年9月5日 上午9:43:44
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return Double数组
	 */
	public static List<Double> strToDoubleList(String str, String separator) {
		return strToList(str, separator, Double::valueOf);
	}

	/**
	 * 说明：字符串转String数组. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年9月5日 上午9:43:44
	 * @param str
	 *            字符串
	 * @param separator
	 *            分隔符
	 * @return String数组
	 */
	public static List<String> strToStringList(String str, String separator) {
		return strToList(str, separator, String::valueOf);
	}

	/*
	 * public static void main(String[] args) { String str = "1,2,3,4,5"; String
	 * separator = ","; List<Integer> strToList = strToList(str, separator,
	 * Integer::valueOf); System.out.println(strToList); }
	 */
}
