package com.shanke.service;

import com.shanke.entity.User;

public interface UserService extends BaseService<User> {

	User findByUsername(String username);

	User findByEmail(String email);

	int updateEmailById(String email, Long id);

	int updatePasswordByUsername(String password, String username);
}