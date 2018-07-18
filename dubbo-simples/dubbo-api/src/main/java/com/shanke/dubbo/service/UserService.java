package com.shanke.dubbo.service;

import com.shanke.dubbo.entity.User;

public interface UserService extends BaseService<User> {

	User findByUsername(String username);

	User findByEmail(String email);

	int updateEmailById(String email, Long id);

	int updatePasswordByUsername(String password, String username);
}