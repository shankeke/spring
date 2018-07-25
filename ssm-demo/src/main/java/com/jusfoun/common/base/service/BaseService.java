package com.jusfoun.common.base.service;

import java.util.List;

import com.jusfoun.common.base.page.IPage;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.mybatis.mapper.MyMapper;

/**
 * 描述 : 通用业务层接口，实现了常用的方法，子接口继承改接口省去常用方法开发工作量. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月7日 上午10:35:45
 */
public interface BaseService<T> extends MyMapper<T> {

	/**
	 * 描述: 批量保存，只存非空字段，空字段使用数据库默认值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年4月28日 上午10:18:14
	 * @param recordList
	 *            保存数据
	 * @return 保存条数
	 */
	int insertListSelective(List<T> recordList) throws ServiceException;

	/**
	 * 描述 : 根据条件分页查询. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月7日 上午10:39:59
	 * @param s
	 *            条件
	 * @param page
	 *            分页，page中可以设置排序条件
	 * @return 数据集合
	 */
	List<T> selectByPage(T s, IPage<T> page) throws ServiceException;

	/**
	 * 描述 : 根据条件分页查询. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月12日 上午9:17:55
	 * @param example
	 *            参数
	 * @param page
	 *            分页，page中可以设置排序条件
	 * @return 数据集合
	 */
	List<T> selectByExampleAndPage(Object example, IPage<T> page) throws ServiceException;

	/**
	 * 描述 : 根据条件分页查询. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月7日 上午10:39:59
	 * @param s
	 *            条件
	 * @param page
	 *            分页，page中可以设置排序条件
	 * @return 分页数据和分页信息
	 */
	IPage<T> selectPage(T s, IPage<T> page) throws ServiceException;

	/**
	 * 描述 : 根据条件分页查询. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月7日 上午10:39:59
	 * @param example
	 *            条件
	 * @param page
	 *            分页，page中可以设置排序条件
	 * @return 分页数据和分页信息
	 */
	IPage<T> selectPageByExample(Object example, IPage<T> page) throws ServiceException;

	/**
	 * 描述 : 根据条件查询并排序. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月20日 下午2:12:56
	 * @param record
	 *            条件
	 * @param orderByClause
	 *            排序条件，排序字段取自数据库字段名而不是实体属性名
	 * @return 结果集
	 * @throws ServiceException
	 */
	List<T> select(T record, String orderByClause) throws ServiceException;

	/**
	 * 描述 : 查询所有并排序. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月20日 下午2:13:43
	 * @param orderByClause
	 *            排序条件，排序字段取自数据库字段名而不是实体属性名
	 * @return 结果集
	 * @throws ServiceException
	 */
	List<T> selectAll(String orderByClause) throws ServiceException;

	/**
	 * 描述 : 根据主键数组批量查询. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月10日 下午1:41:54
	 * @param keys
	 *            主键数组
	 * @return 查询的结果集合
	 * @throws ServiceException
	 */
	List<T> selectByPrimaryKeys(Object... keys) throws ServiceException;

	/**
	 * 描述 :根据主键批量删除. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月10日 下午1:41:12
	 * @param keys
	 *            主键数组
	 * @return 删除的条数
	 * @throws ServiceException
	 */
	int deleteByPrimaryKeys(Object... keys) throws ServiceException;
 
}
