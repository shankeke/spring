package com.jusfoun.common.base.service.impl;

import java.util.List;

import com.jusfoun.common.base.service.IdsService;

import tk.mybatis.mapper.common.IdsMapper;

/**
 * 描述 :存在ID属性的实体批量操作. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年12月15日 下午5:59:34
 */
public abstract class IdsServiceImpl<T> implements IdsService<T> {

	private IdsMapper<T> idsMapper;

	@Override
	public List<T> selectByIds(String ids) {
		return idsMapper.selectByIds(ids);
	}

	@Override
	public int deleteByIds(String ids) {
		return idsMapper.deleteByIds(ids);
	}

}
