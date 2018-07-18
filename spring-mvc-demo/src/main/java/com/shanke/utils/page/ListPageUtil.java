package com.shanke.utils.page;

import java.util.Collections;
import java.util.List;

/**
 * 描述 :集合数据排序分页. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2017-2-8 下午3:23:45
 * @version 1.0
 */
public class ListPageUtil<T extends Comparable<? super T>> {

	/**
	 * 描述 : 集合排序，集合元素必须实现Comparable接口. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2017-2-8 下午3:25:10
	 * @param list
	 *            需要排序的集合
	 * @param asc
	 *            是否正序排列
	 * @return 排序后的集合
	 */
	public List<T> sort(List<T> list, boolean asc) {
		// 正序排列
		Collections.sort(list);
		if (!asc)// 如果是需要逆序排列，翻转正序的结果即可
			Collections.reverse(list);
		return list;
	}

	/**
	 * 描述 : 集合排序，集合元素必须实现Comparable接口. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2017-2-8 下午3:25:10
	 * @param list
	 *            需要排序的集合
	 * @return 排序后的集合
	 */
	public List<T> sort(List<T> list) {
		return sort(list, true);
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
	public List<T> page(List<T> list, int pageNum, int pageSize, boolean limit) {
		if (list == null || list.size() == 0)
			return null;
		if (pageNum <= 0)
			pageNum = 1;
		if (pageSize <= 0)
			pageNum = 10;

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
	public List<T> page(List<T> list, int pageNum, int pageSize) {
		return page(list, pageNum, pageSize, false);
	}
}
