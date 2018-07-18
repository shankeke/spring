package com.shanke.dubbo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.shanke.dubbo.entity.User;
import com.shanke.dubbo.mapper.UserMapper;
import com.shanke.dubbo.service.UserService;

@Component("userService")
@Service
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
