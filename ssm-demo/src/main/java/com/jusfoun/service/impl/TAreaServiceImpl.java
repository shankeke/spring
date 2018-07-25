package com.jusfoun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jusfoun.common.base.service.impl.BaseWithAssociateServiceImpl;
import com.jusfoun.common.cache.CacheConsts;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.util.list.IListUtil;
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
public class TAreaServiceImpl extends BaseWithAssociateServiceImpl<TArea> implements TAreaService {

	@Autowired
	private TAreaMapper tAreaMapper;

	@Override
	public TArea selectAreaTree(String areaName) throws ServiceException {
		TArea root = tAreaMapper.selectTreeRoot(0L);
		List<TArea> searchList = root.search(root.getSubList(), areaName);
		if (IListUtil.hasData(searchList) || root.isMatch(root, areaName)) {
			root.setSubList(searchList);
			return root;
		}
		return null;
	}

	@Cacheable(value = CacheConsts.CACHE_TEMP, key = "'area_cache_' + #id", unless = "#result == null")
	@Override
	public TArea selectTreeById(Long id) throws ServiceException {
		return tAreaMapper.selectTreeRoot(0L);
	}
}
