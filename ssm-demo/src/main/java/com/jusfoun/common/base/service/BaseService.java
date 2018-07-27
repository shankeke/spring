package com.jusfoun.common.base.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.jusfoun.common.base.page.IPage;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.mybatis.mapper.MyBaseMapper;

/**
 * 描述 : 业务层实现基类. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月12日 上午10:55:07
 */
@Transactional
public interface BaseService<T> extends MyBaseMapper<T> {

	MyBaseMapper<T> getMyBaseMapper();

	@Override
	default int insert(T record) {
		return getMyBaseMapper().insert(record);
	}

	@Override
	default int insertSelective(T record) {
		return getMyBaseMapper().insertSelective(record);
	}

	@Override
	default int insertList(List<T> recordList) {
		return getMyBaseMapper().insertList(recordList);
	}

	/**
	 * 描述:批量插入，空字段使用默认值. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月27日 下午4:20:27
	 * @param recordList
	 *            对象集合
	 * @return 插入结果
	 * @throws ServiceException
	 */
	default int insertListSelective(List<T> recordList) throws ServiceException {
		int i = 0;
		for (T t : recordList) {
			i += insertSelective(t);
		}
		return i;
	}

	@Override
	default int updateByExample(T record, Object example) {
		return getMyBaseMapper().updateByExampleSelective(record, example);
	}

	@Override
	default int updateByExampleSelective(T record, Object example) {
		return getMyBaseMapper().updateByExampleSelective(record, example);
	}

	@Override
	default int delete(T record) {
		return getMyBaseMapper().delete(record);
	}

	@Override
	default int deleteList(List<T> recordList) {
		return getMyBaseMapper().deleteList(recordList);
	}

	@Override
	default int deleteByExample(Object example) {
		return getMyBaseMapper().deleteByExample(example);
	}

	@Override
	default T selectOne(T record) {
		return getMyBaseMapper().selectOne(record);
	}

	@Override
	default List<T> select(T record) {
		return getMyBaseMapper().select(record);
	}

	/**
	 * 描述: <方法描述>. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月27日 下午4:21:22
	 * @param record
	 *            查询条件包装
	 * @param orderByClause
	 *            排序字段，如：id asc, name desc
	 * @return 查询数据集合
	 */
	default List<T> select(T record, String orderByClause) {
		startOrderBy(orderByClause);
		return select(record);
	}

	@Override
	default List<T> selectAll() {
		return getMyBaseMapper().selectAll();
	}

	/**
	 * 描述: 查询所有数据并排序. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月27日 下午4:22:20
	 * @param orderByClause
	 *            排序字段，如：id asc, name desc
	 * @return 查询数据集合
	 */
	default List<T> selectAll(String orderByClause) {
		startOrderBy(orderByClause);
		return selectAll();
	}

	@Override
	default T selectOneByExample(Object example) {
		return getMyBaseMapper().selectOneByExample(example);
	}

	@Override
	default List<T> selectByExample(Object example) {
		return getMyBaseMapper().selectByExample(example);
	}

	@Override
	default List<T> selectByRowBounds(T record, RowBounds rowBounds) {
		return getMyBaseMapper().selectByRowBounds(record, rowBounds);
	}

	@Override
	default List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
		return getMyBaseMapper().selectByExampleAndRowBounds(example, rowBounds);
	}

	/**
	 * 描述: 根据条件查询分页数据. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月27日 下午4:22:55
	 * @param s
	 *            查询条件
	 * @param page
	 *            分页信息
	 * @return 查询结果集合
	 */
	default List<T> selectByPage(T s, IPage<T> page) {
		startPage(page);
		return select(s);
	}

	/**
	 * 描述: 根据条件查询分页数据. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月27日 下午4:22:55
	 * @param s
	 *            查询条件
	 * @param page
	 *            分页信息
	 * @return 查询结果，使用分页对象包装
	 */
	default IPage<T> selectPage(T s, IPage<T> page) {
		int totalCount = selectCount(s);
		if (totalCount <= 0)
			return page;

		if (page == null) {// 查询所有
			page = new IPage<T>();
			page.setTotalCount(totalCount);
			page.setList(select(s));
		} else {// 分页查询
			page.setTotalCount(totalCount);
			page.setList(selectByPage(s, page));
		}
		return page;
	}

	/**
	 * 描述: 使用example对象查询分页数据. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月27日 下午4:22:55
	 * @param s
	 *            查询条件
	 * @param page
	 *            分页信息
	 * @return 查询结果集合
	 */
	default List<T> selectByExampleAndPage(Object example, IPage<T> page) {
		startPage(page);
		return selectByExample(example);
	}

	/**
	 * 描述: 使用example对象查询分页数据. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月27日 下午4:22:55
	 * @param s
	 *            查询条件
	 * @param page
	 *            分页信息
	 * @return 查询结果，使用分页对象包装
	 */
	default IPage<T> selectPageByExample(Object example, IPage<T> page) {
		int totalCount = selectCountByExample(example);
		if (totalCount <= 0)
			return page;

		if (page == null) {// 查询所有
			page = new IPage<T>();
			page.setTotalCount(totalCount);
			page.setList(selectByExample(example));
		} else {// 分页查询
			page.setTotalCount(totalCount);
			page.setList(selectByExampleAndPage(example, page));
		}
		return page;
	}

	@Override
	default int selectCount(T record) {
		return getMyBaseMapper().selectCount(record);
	}

	@Override
	default int selectCountByExample(Object example) {
		return getMyBaseMapper().selectCountByExample(example);
	}

	/**
	 * 描述:分页排序. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月26日 上午10:16:26
	 * @param page
	 *            分页信息
	 */
	default void startPage(IPage<T> page) {
		if (page != null) {
			startPage(page.getPageNum(), page.getPageSize(), page.getOrderByClause());
		}
	}

	/**
	 * 描述: 分页排序. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月26日 上午10:35:41
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            页长
	 * @param orderByClause
	 *            排序
	 */
	default void startPage(int pageNum, int pageSize, String orderByClause) {
		PageHelper.startPage(pageNum, pageSize, orderByClause);
	}

	/**
	 * 描述:分页排序. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月26日 上午10:16:26
	 * @param page
	 *            分页信息
	 */
	default void startOrderBy(String orderByClause) {
		if (StringUtils.isNotEmpty(orderByClause)) {
			PageHelper.orderBy(orderByClause);
		}
	}

}
