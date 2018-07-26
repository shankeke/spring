package com.jusfoun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jusfoun.common.base.service.impl.BaseEntityWithAssociateServiceImpl;
import com.jusfoun.common.exception.ServiceException;
import com.jusfoun.common.util.entry.EntityUtils;
import com.jusfoun.entity.SysGov;
import com.jusfoun.mapper.ds0.SysGovMapper;
import com.jusfoun.service.SysGovService;

/**
 * 描述 :组织机构管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 上午10:04:49
 */
@Service
public class SysGovServiceImpl extends BaseEntityWithAssociateServiceImpl<SysGov> implements SysGovService {

	@Autowired
	private SysGovMapper sysGovMapper;

	@Override
	public String selectNameByPrimaryKey(Long id) throws ServiceException {
		return sysGovMapper.selectNameByPrimaryKey(id);
	}

	@Override
	public SysGov selectTree(Long rootId) throws ServiceException {
		return sysGovMapper.selectTree(EntityUtils.getDefaultIfNull(rootId, 0L));
	}
}
