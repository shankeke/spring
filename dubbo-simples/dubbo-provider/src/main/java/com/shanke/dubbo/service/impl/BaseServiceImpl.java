package com.shanke.dubbo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.shanke.dubbo.comm.MyMapper;
import com.shanke.dubbo.comm.vo.Page;
import com.shanke.dubbo.service.BaseService;
import com.shanke.dubbo.utils.reflect.GenericsUtils;

/**
 * 描述 : 公共的业务层实现. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-10-28 下午5:51:59
 * @version 1.0
 */
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected MyMapper<T> myMapper;

	public MyMapper<T> getMyMapper() {
		return myMapper;
	}

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		Class<T> clazz = GenericsUtils.getSuperClassGenricType(getClass(), 0);
		logger.debug("create class - " + getClass() + ", T - "
				+ clazz.getName());
	}

	@Override
	public T selectOne(T record) {
		// TODO Auto-generated method stub
		return myMapper.selectOne(record);
	}

	@Override
	public List<T> select(T record) {
		// TODO Auto-generated method stub
		return myMapper.select(record);
	}

	@Override
	public List<T> selectAll() {
		// TODO Auto-generated method stub
		return myMapper.selectAll();
	}

	@Override
	public List<T> selectAll(T s, Page page) {
		// TODO Auto-generated method stub
		if (page == null) {
			return myMapper.select(s);
		} else {
			return myMapper.selectByRowBounds(s, page.getRowBounds());
		}
	}

	@Override
	public int selectCount(T record) {
		// TODO Auto-generated method stub
		return myMapper.selectCount(record);
	}

	@Override
	public T selectByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return myMapper.selectByPrimaryKey(key);
	}

	@Override
	public int insert(T record) {
		// TODO Auto-generated method stub
		return myMapper.insert(record);
	}

	@Override
	public int insertSelective(T record) {
		// TODO Auto-generated method stub
		return myMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		// TODO Auto-generated method stub
		return myMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		// TODO Auto-generated method stub
		return myMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(T record) {
		// TODO Auto-generated method stub
		return myMapper.delete(record);
	}

	@Override
	public int deleteByPrimaryKey(Object key) {
		// TODO Auto-generated method stub
		return myMapper.deleteByPrimaryKey(key);
	}

	@Override
	public List<T> selectByExample(Object example) {
		// TODO Auto-generated method stub
		return myMapper.selectByExample(example);
	}

	@Override
	public int selectCountByExample(Object example) {
		// TODO Auto-generated method stub
		return myMapper.selectCountByExample(example);
	}

	@Override
	public int deleteByExample(Object example) {
		// TODO Auto-generated method stub
		return myMapper.deleteByExample(example);
	}

	@Override
	public int updateByExample(T record, Object example) {
		// TODO Auto-generated method stub
		return myMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExampleSelective(T record, Object example) {
		// TODO Auto-generated method stub
		return myMapper.updateByExampleSelective(record, example);
	}

	@Override
	public List<T> selectByExampleAndRowBounds(Object example,
			RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return myMapper.selectByExampleAndRowBounds(example, rowBounds);
	}

	@Override
	public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return myMapper.selectByRowBounds(record, rowBounds);
	}

	@Override
	public int insertList(List<T> recordList) {
		// TODO Auto-generated method stub
		return myMapper.insertList(recordList);
	}

	@Override
	public int insertUseGeneratedKeys(T record) {
		// TODO Auto-generated method stub
		return myMapper.insertUseGeneratedKeys(record);
	}

}
