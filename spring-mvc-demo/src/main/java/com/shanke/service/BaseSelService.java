package com.shanke.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface BaseSelService<T> {
	/**
	 * 描述 : 根据条件查询一条记录 <br>
	 * <p>
	 * 
	 * @param s
	 *            查询条件
	 * @return 符合条件的唯一对象，否则为null
	 * @throws Exception
	 */
	T selectOne(T s) throws Exception;

	/**
	 * 描述 : 根据条件查询所有相应数据. <br>
	 * <p>
	 * 
	 * @param s
	 *            查询条件
	 * @return 结果集
	 * @throws Exception
	 */
	List<T> selectAll(T s, RowBounds rowBounds) throws Exception;

	/**
	 * 描述 : 根据条件查询数据的总量. <br>
	 * <p>
	 * 
	 * @param s
	 *            查询条件
	 * @return 查到的条数
	 * @throws Exception
	 */
	int selectCount(T s) throws Exception;

}
