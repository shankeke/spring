package com.shanke.service;

import com.shanke.entity.Right;

public interface RightService extends BaseService<Right> {

	Right findByName(String name);

	int updateNameById(String name, Long id);

	int updateResumeById(String resume, Long id);

}