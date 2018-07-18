package com.shanke.utils.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 描述 :集合排序分页. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2017-2-8 上午11:31:03
 * @version 1.0
 */
public class PageUtil<K extends Comparable<?>, V> {

	/**
	 * 描述 : 抽取Map映射的值为List集合. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2017-2-8 上午11:24:10
	 * @param map
	 *            排序后的映射集合
	 * @return 从映射集合中提取出来的值集
	 */
	public List<V> extractMap2List(Map<K, V> map) {
		List<V> list = new ArrayList<V>();
		Iterator<Entry<K, V>> iterator = map.entrySet().iterator();
		while (iterator.hasNext())
			list.add(iterator.next().getValue());
		return list;
	}

	/**
	 * 描述 : Map集合排序. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2017-2-8 上午11:22:58
	 * @param map
	 *            未排序的集合
	 * @param asc
	 *            排序方式，true正序，false逆序
	 * @return 排序后的集合
	 */
	public Map<K, V> order(Map<K, V> map, boolean asc) {
		Map<K, V> sortMap = null;
		if (asc)
			sortMap = new TreeMap<K, V>();
		else
			sortMap = new TreeMap<K, V>(Collections.reverseOrder());

		sortMap.putAll(map);
		return sortMap;
	}

	/**
	 * 描述 : Map集合排序，默认正序排列. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2017-2-8 上午11:22:58
	 * @param map
	 *            未排序的集合
	 * @return 排序后的集合
	 */
	public Map<K, V> order(Map<K, V> map) {
		return order(map, true);
	}

	/**
	 * 描述 :集合分页. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2017-2-8 上午11:26:43
	 * @param list
	 *            数据总集，未分页，但已经排过序（如果需要排序的话）
	 * @param pageNum
	 *            当前页码
	 * @param pageSize
	 *            每页条数
	 * @param limit
	 *            是否限制最大页码
	 * @return 分页后对应页码内的数据集合
	 */
	public List<V> page(List<V> list, int pageNum, int pageSize, boolean limit) {
		if (list == null || list.size() == 0)
			return null;

		int allCount = list.size();
		// 如果限制页码不能大于最大页码，如果请求的页码大于最大页码，这设置页码为最大页码的值， 否则则不用设置，根据请求的数值计算
		if (limit) {
			int pageCount = (allCount + pageSize - 1) / pageSize;
			if (pageNum >= pageCount)
				pageNum = pageCount;
		}

		int start = (pageNum - 1) * pageSize;
		// 如果请求数据超过总的数据量，返回空
		if (start > allCount)
			return null;

		int end = pageNum * pageSize;
		if (end >= allCount)
			end = allCount;

		return list.subList(start, end);
	}

	/**
	 * 描述 :集合分页，默认不限制页码，超过最大页码后返回空集. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2017-2-8 上午11:26:43
	 * @param list
	 *            数据总集，未分页，但已经排过序（如果需要排序的话）
	 * @param pageNum
	 *            当前页码
	 * @param pageSize
	 *            每页条数
	 * @param limit
	 *            是否限制最大页码
	 * @return 分页后对应页码内的数据集合
	 */
	public List<V> page(List<V> list, int pageNum, int pageSize) {
		return page(list, pageNum, pageSize, false);
	}
}
