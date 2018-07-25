package com.jusfoun.common.base.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.jusfoun.common.base.page.IPage;
import com.jusfoun.common.base.service.BaseService;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.mybatis.mapper.MyMapper;

/**
 * 描述 : 业务层实现基类. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月12日 上午10:55:07
 */
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	@Autowired
	protected MyMapper<T> myMapper;

	@Override
	public int insert(T record) {
		return myMapper.insert(record);
	}

	@Override
	public int insertSelective(T record) {
		return myMapper.insertSelective(record);
	}

	@Override
	public int insertList(List<T> recordList) {
		return myMapper.insertList(recordList);
	}

	@Override
	public int insertListSelective(List<T> recordList) throws ServiceException {
		int i = 0;
		for (T t : recordList) {
			i += insertSelective(t);
		}
		return i;
	}

	@Override
	public int insertListWithPrimaryKey(List<T> recordList) {
		return myMapper.insertListWithPrimaryKey(recordList);
	}

	@Override
	public int insertUseGeneratedKeys(T record) {
		return myMapper.insertUseGeneratedKeys(record);
	}

	@Override
	public int replace(T record) {
		return myMapper.replace(record);
	}

	@Override
	public int replaceListWithPrimaryKey(List<T> recordList) {
		return myMapper.replaceListWithPrimaryKey(recordList);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		return myMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return myMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByExample(T record, Object example) {
		return myMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExampleSelective(T record, Object example) {
		return myMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateListByPrimaryKey(List<T> recordList) {
		return myMapper.updateListByPrimaryKey(recordList);
	}

	@Override
	public int updateListByPrimaryKeySelective(List<T> recordList) {
		return myMapper.updateListByPrimaryKeySelective(recordList);
	}

	@Override
	public int delete(T record) {
		return myMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		return myMapper.deleteByPrimaryKey(key);
	}

	@Override
	public int deleteList(List<T> recordList) {
		return myMapper.deleteList(recordList);
	}

	@Override
	public int deleteListByPrimaryKey(List<T> recordList) {
		return myMapper.deleteListByPrimaryKey(recordList);
	}

	@Override
	public int deleteByPrimaryKeys(List<?> keys) {
		return myMapper.deleteByPrimaryKeys(keys);
	}

	@Override
	public int deleteByPrimaryKeys(Object... keys) {
		return deleteByPrimaryKeys(Arrays.asList(keys));
	}

	@Override
	public int deleteByExample(Object example) {
		return myMapper.deleteByExample(example);
	}

	@Override
	public T selectByPrimaryKey(Object key) {
		return myMapper.selectByPrimaryKey(key);
	}

	@Override
	public List<T> selectByPrimaryKeys(List<?> keys) {
		return myMapper.selectByPrimaryKeys(keys);
	}

	@Override
	public List<T> selectByPrimaryKeys(Object... keys) {
		return selectByPrimaryKeys(Arrays.asList(keys));
	}

	@Override
	public T selectOne(T record) {
		return myMapper.selectOne(record);
	}

	@Override
	public List<T> select(T record) {
		return myMapper.select(record);
	}

	@Override
	public List<T> select(T record, String orderByClause) {
		startOrderBy(orderByClause);
		return select(record);
	}

	@Override
	public List<T> selectAll() {
		return myMapper.selectAll();
	}

	@Override
	public List<T> selectAll(String orderByClause) {
		startOrderBy(orderByClause);
		return selectAll();
	}

	@Override
	public T selectOneByExample(Object example) {
		return myMapper.selectOneByExample(example);
	}

	@Override
	public List<T> selectByExample(Object example) {
		return myMapper.selectByExample(example);
	}

	@Override
	public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
		return myMapper.selectByRowBounds(record, rowBounds);
	}

	@Override
	public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
		return myMapper.selectByExampleAndRowBounds(example, rowBounds);
	}

	@Override
	public List<T> selectByPage(T s, IPage<T> page) {
		startPage(page);
		return select(s);
	}

	@Override
	public IPage<T> selectPage(T s, IPage<T> page) {
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

	@Override
	public List<T> selectByExampleAndPage(Object example, IPage<T> page) {
		startPage(page);
		return selectByExample(example);
	}

	@Override
	public IPage<T> selectPageByExample(Object example, IPage<T> page) {
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
	public int selectCount(T record) {
		return myMapper.selectCount(record);
	}

	@Override
	public int selectCountByExample(Object example) {
		return myMapper.selectCountByExample(example);
	}

	@Override
	public boolean existsWithPrimaryKey(Object key) {
		return myMapper.existsWithPrimaryKey(key);
	}

	/**
	 * 描述:分页排序. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年1月26日 上午10:16:26
	 * @param page
	 *            分页信息
	 */
	protected void startPage(IPage<T> page) {
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
	protected void startPage(int pageNum, int pageSize, String orderByClause) {
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
	protected void startOrderBy(String orderByClause) {
		if (StringUtils.isNotEmpty(orderByClause)) {
			PageHelper.orderBy(orderByClause);
		}
	}

}
