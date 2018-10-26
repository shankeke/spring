package com.jusfoun.common.base.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jusfoun.common.base.extend.annotation.PreInsert;
import com.jusfoun.common.base.extend.annotation.PreUpdate;
import com.jusfoun.common.base.extend.annotation.Preprocess;
import com.jusfoun.common.mybatis.mapper.MyIdableMapper;

/**
 * 说明： 存在ID属性的实体业务层实现基类. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月12日 上午10:55:07
 */
@Transactional
public interface BaseIdableService<T> extends BaseService<T>, MyIdableMapper<T> {

	MyIdableMapper<T> getMyIdableMapper();

	@Preprocess
	@Override
	default int insertListWithPrimaryKey(@PreInsert List<T> recordList) {
		return getMyIdableMapper().insertListWithPrimaryKey(recordList);
	}

	@Preprocess
	@Override
	default int insertUseGeneratedKeys(@PreInsert T record) {
		return getMyIdableMapper().insertUseGeneratedKeys(record);
	}

	@Preprocess
	@Override
	default int replace(@PreInsert T record) {
		return getMyIdableMapper().replace(record);
	}

	@Preprocess
	@Override
	default int replaceListWithPrimaryKey(@PreInsert List<T> recordList) {
		return getMyIdableMapper().replaceListWithPrimaryKey(recordList);
	}

	@Preprocess
	@Override
	default int updateByPrimaryKey(@PreUpdate T record) {
		return getMyIdableMapper().updateByPrimaryKey(record);
	}

	@Preprocess
	@Override
	default int updateByPrimaryKeySelective(@PreUpdate T record) {
		return getMyIdableMapper().updateByPrimaryKeySelective(record);
	}

	@Preprocess
	@Override
	default int updateListByPrimaryKey(@PreUpdate List<T> recordList) {
		return getMyIdableMapper().updateListByPrimaryKey(recordList);
	}

	@Preprocess
	@Override
	default int updateListByPrimaryKeySelective(@PreUpdate List<T> recordList) {
		return getMyIdableMapper().updateListByPrimaryKeySelective(recordList);
	}

	@Override
	default int deleteByPrimaryKey(Object key) {
		return getMyIdableMapper().deleteByPrimaryKey(key);
	}

	@Override
	default int deleteListByPrimaryKey(List<T> recordList) {
		return getMyIdableMapper().deleteListByPrimaryKey(recordList);
	}

	@Override
	default int deleteByPrimaryKeys(List<?> keys) {
		return getMyIdableMapper().deleteByPrimaryKeys(keys);
	}

	/**
	 * 说明：根据主键值数组批量删除. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月27日 下午4:25:52
	 * @param keys
	 *            主键值数组
	 * @return 删除结果
	 */
	default int deleteByPrimaryKeys(Object... keys) {
		return deleteByPrimaryKeys(Arrays.asList(keys));
	}

	@Override
	default T selectByPrimaryKey(Object key) {
		return getMyIdableMapper().selectByPrimaryKey(key);
	}

	@Override
	default List<T> selectByPrimaryKeys(List<?> keys) {
		return getMyIdableMapper().selectByPrimaryKeys(keys);
	}

	/**
	 * 说明：根据主键值数组查询. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年7月27日 下午4:26:26
	 * @param keys
	 *            主键值数组
	 * @return 数据结果集合
	 */
	default List<T> selectByPrimaryKeys(Object... keys) {
		return selectByPrimaryKeys(Arrays.asList(keys));
	}

	@Override
	default boolean existsWithPrimaryKey(Object key) {
		return getMyIdableMapper().existsWithPrimaryKey(key);
	}

	@Override
	default int deleteByIds(String ids) {
		return getMyIdableMapper().deleteByIds(ids);
	}

	@Override
	default List<T> selectByIds(String ids) {
		return getMyIdableMapper().selectByIds(ids);
	}

}
