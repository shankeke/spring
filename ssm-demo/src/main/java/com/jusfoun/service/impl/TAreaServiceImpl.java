package com.jusfoun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jusfoun.common.cache.CacheConsts;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.mybatis.mapper.MyBaseMapper;
import com.jusfoun.common.mybatis.mapper.MyIdableMapper;
import com.jusfoun.common.mybatis.mapper.base.BaseWithAssociateSelectMapper;
import com.jusfoun.common.util.entry.EntityUtils;
import com.jusfoun.entity.TArea;
import com.jusfoun.mapper.ds1.TAreaMapper;
import com.jusfoun.service.TAreaService;

/**
 * 描述: 地区管理业务接口实现. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2018年7月24日 上午11:05:19
 */
@Service
public class TAreaServiceImpl implements TAreaService {

	@Autowired
	private TAreaMapper tAreaMapper;

	@Override
	public BaseWithAssociateSelectMapper<TArea> getBaseWithAssociateSelectMapper() {
		return tAreaMapper;
	}

	@Override
	public MyIdableMapper<TArea> getMyIdableMapper() {
		return tAreaMapper;
	}

	@Override
	public MyBaseMapper<TArea> getMyBaseMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Cacheable(value = CacheConsts.CACHE_TEMP, key = "'area_cache_' + #id", unless = "#result == null")
	@Override
	public TArea selectTree(Long rootId) throws ServiceException {
		return tAreaMapper.selectTree(EntityUtils.getDefaultIfNull(rootId, 0L));
	}
}
