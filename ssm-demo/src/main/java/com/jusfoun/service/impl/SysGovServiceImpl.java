package com.jusfoun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jusfoun.common.base.service.impl.BaseEntityWithAssociateServiceImpl;
import com.jusfoun.entity.SysGov;
import com.jusfoun.entity.SysUser;
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
	public String selectNameByPrimaryKey(Integer id) {
		return sysGovMapper.selectNameByPrimaryKey(id);
	}

	@Override
	public List<SysUser> selectByParentId(Integer parentId) {
		return sysGovMapper.selectByParentId(parentId);
	}
}
