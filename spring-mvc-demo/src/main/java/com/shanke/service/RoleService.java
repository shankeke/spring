package com.shanke.service;

import com.shanke.entity.Role;

public interface RoleService extends BaseService<Role> {

	Role findByName(String name);

	int updateNameById(String name, Long id);

	int updateResumeById(String resume, Long id);
}