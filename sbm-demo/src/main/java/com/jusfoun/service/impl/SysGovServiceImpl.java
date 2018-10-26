package com.jusfoun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.common.mybatis.mapper.MyBaseMapper;
import com.jusfoun.common.mybatis.mapper.MyIdableMapper;
import com.jusfoun.common.mybatis.mapper.extension.BaseExtensionSelectMapper;
import com.jusfoun.common.utils.EntityUtils;
import com.jusfoun.entity.SysGov;
import com.jusfoun.mapper.ds0.SysGovMapper;
import com.jusfoun.service.SysGovService;

/**
 * 说明：组织机构管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午10:04:49
 */
@Service
public class SysGovServiceImpl implements SysGovService {

	@Autowired
	private SysGovMapper sysGovMapper;

	@Override
	public BaseExtensionSelectMapper<SysGov> getBaseExtensionSelectMapper() {
		return sysGovMapper;
	}

	@Override
	public MyIdableMapper<SysGov> getMyIdableMapper() {
		return sysGovMapper;
	}

	@Override
	public MyBaseMapper<SysGov> getMyBaseMapper() {
		return sysGovMapper;
	}

	@Override
	public String selectNameByPrimaryKey(Long id) throws ServiceException {
		return sysGovMapper.selectNameByPrimaryKey(id);
	}

	@Override
	public SysGov selectTree(Long rootId) throws ServiceException {
		return sysGovMapper.selectTree(EntityUtils.getDefaultIfNull(rootId, 0L));
	}
}
