package com.jusfoun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jusfoun.common.base.tree.TreeableMapper;
import com.jusfoun.common.cache.CacheConsts;
import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.common.mybatis.mapper.MyBaseMapper;
import com.jusfoun.common.mybatis.mapper.MyIdableMapper;
import com.jusfoun.common.mybatis.mapper.extension.BaseExtensionSelectMapper;
import com.jusfoun.entity.TArea;
import com.jusfoun.mapper.ds0.TAreaMapper;
import com.jusfoun.service.TAreaService;

/**
 * 说明： 地区管理业务接口实现. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 上午11:05:19
 */
@Service
public class TAreaServiceImpl implements TAreaService {

	@Autowired
	private TAreaMapper tAreaMapper;

	@Override
	public BaseExtensionSelectMapper<TArea> getBaseExtensionSelectMapper() {
		return tAreaMapper;
	}

	@Override
	public MyIdableMapper<TArea> getMyIdableMapper() {
		return tAreaMapper;
	}

	@Override
	public MyBaseMapper<TArea> getMyBaseMapper() {
		return tAreaMapper;
	}

	@Override
	public TreeableMapper<TArea, Long> getTreeableMapper() {
		return tAreaMapper;
	}

	@Cacheable(value = CacheConsts.CACHE_PERSISTENT, unless = "#result == null")
	@Override
	public TArea selectTree(Long rootId) throws ServiceException {
		return TAreaService.super.selectTree(rootId);
	}

	@Cacheable(value = CacheConsts.CACHE_PERSISTENT, unless = "#result == null")
	@Override
	public List<TArea> selectByParentId(Long parentId) throws ServiceException {
		return TAreaService.super.selectByParentId(parentId);
	}

}
