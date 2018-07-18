package com.shanke.dubbo.service;

import com.shanke.dubbo.entity.Role;

public interface RoleService extends BaseService<Role> {

	Role findByName(String name);

	int updateNameById(String name, Long id);

	int updateResumeById(String resume, Long id);
}