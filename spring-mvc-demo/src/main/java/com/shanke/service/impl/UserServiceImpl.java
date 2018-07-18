package com.shanke.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shanke.entity.User;
import com.shanke.mapper.UserMapper;
import com.shanke.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		User record = new User();
		record.setUsername(username);
		return userMapper.selectOne(record);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setEmail(email);
		return userMapper.selectOne(user);
	}

	@Override
	public int updateEmailById(String email, Long id) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int updatePasswordByUsername(String password, String username) {
		// TODO Auto-generated method stub

		// 参数
		User record = new User();
		record.setPassword(password);

		// 条件
		User example = new User();
		example.setUsername(username);

		// 更新
		return userMapper.updateByExampleSelective(record, example);
	}

}
