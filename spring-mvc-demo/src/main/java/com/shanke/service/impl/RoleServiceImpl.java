package com.shanke.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shanke.entity.Role;
import com.shanke.mapper.RoleMapper;
import com.shanke.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {

	@Resource
	private RoleMapper roleMapper;

	@Override
	public Role findByName(String name) {
		// TODO Auto-generated method stub
		Role record = new Role();
		record.setName(name);
		return roleMapper.selectOne(record);
	}

	@Override
	public int updateNameById(String name, Long id) {
		// TODO Auto-generated method stub
		Role record = new Role();
		record.setId(id);
		record.setName(name);
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateResumeById(String resume, Long id) {
		// TODO Auto-generated method stub
		Role record = new Role();
		record.setId(id);
		record.setResume(resume);
		return roleMapper.updateByPrimaryKeySelective(record);
	}

}
