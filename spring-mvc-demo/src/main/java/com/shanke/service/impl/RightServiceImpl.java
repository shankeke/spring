package com.shanke.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shanke.entity.Right;
import com.shanke.mapper.RightMapper;
import com.shanke.service.RightService;

@Service("rightService")
public class RightServiceImpl extends BaseServiceImpl<Right> implements
		RightService {

	@Resource
	private RightMapper rightMapper;

	@Override
	public Right findByName(String name) {
		// TODO Auto-generated method stub
		Right record = new Right();
		record.setName(name);
		return rightMapper.selectOne(record);
	}

	@Override
	public int updateNameById(String name, Long id) {
		// TODO Auto-generated method stub
		Right record = new Right();
		record.setId(id);
		record.setName(name);
		return rightMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateResumeById(String resume, Long id) {
		// TODO Auto-generated method stub
		Right record = new Right();
		record.setId(id);
		record.setResume(resume);
		return rightMapper.updateByPrimaryKeySelective(record);
	}
}
