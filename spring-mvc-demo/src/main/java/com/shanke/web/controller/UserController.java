//package com.shanke.web.controller;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.data.domain.Sort.Order;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.shanke.entity.User;
//import com.shanke.service.UserService;
//
///**
// * 描述 : 用户管理请求. <br>
// * <p>
// * 
// * Copyright (c) 2016 优识云创(YSYC)
// * 
// * @author Uknower-yjw
// * @date 2016年7月30日 下午8:48:58
// */
//@RestController
//@RequestMapping("/user")
//public class UserController {
//	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
//
//	@Resource
//	private UserService userService;
//
//	// http://localhost:8080/user/insert?username=yjw&password=yjW123321&phone=13520439383&age=27&email=13460286086@163.com
//	@RequestMapping(value = "/insert", method = RequestMethod.GET)
//	@ResponseBody
//	public User insert(User user) {
//		logger.debug("保存用户信息：" + user.toString());
//		return userService.save(user);
//	}
//
//	// http://localhost:8080/user/get-by-username?username=yjw
//	@RequestMapping(value = "/get-by-username", method = RequestMethod.GET)
//	@ResponseBody
//	public User getByUsername(@RequestParam(name = "username", required = true) String username) {
//		User user = userService.findByUsername(username);
//		logger.debug("查询用户信息：username-" + username + " ,user-" + user.toString());
//		return user;
//	}
//
//	// http://localhost:8080/user/del-by-id/1
//	@RequestMapping(value = "/del-by-id/{id}", method = RequestMethod.GET)
//	@ResponseBody
//	public int delById(@PathVariable("id") Long id) {
//		logger.debug("删除用户信息：id-" + id);
//		userService.delete(id);
//		return 1;
//	}
//
//	// http://localhost:8080/user/del-by-id/1
//	@RequestMapping(value = "/del-by-username/{username}", method = RequestMethod.GET)
//	@ResponseBody
//	public int delById(@PathVariable("username") String username) {
//		User user = userService.findByUsername(username);
//		userService.delete(user);
//		logger.debug("删除用户信息：username-" + username + " ,user-" + user.toString());
//		return 1;
//	}
//
//	// http://localhost:8080/user/get-by-id/2
//	@RequestMapping(value = "/get-by-id/{id}", method = RequestMethod.GET)
//	@ResponseBody
//	public User getById(@PathVariable("id") Long id) {
//		User user = userService.findOne(id);
//		logger.debug("查询用户信息：id-" + id + " ,user-" + user.toString());
//		return user;
//	}
//
//	// http://localhost:8080/user/get-by-email?email=13460286086@163.com
//	@RequestMapping(value = "/get-by-email", method = RequestMethod.GET)
//	@ResponseBody
//	public User getByEmail(@RequestParam(name = "email", required = true) String email) {
//		User user = userService.findByEmail(email);
//		logger.debug("查询用户信息：email-" + email + " ,user-" + user.toString());
//		return user;
//	}
//
//	// http://localhost:8080/user/get-by-page?email=13460286086@163.com
//	@RequestMapping(value = "/get-by-page", method = RequestMethod.GET)
//	@ResponseBody
//	public Page<User> getByPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
//			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
//		Page<User> page = userService
//				.findAll(new PageRequest(pageNum, pageSize, new Sort(new Order(Direction.ASC, "id"),
//						new Order(Direction.ASC, "username"), new Order(Direction.DESC, "age"))));
//		logger.debug("查询用户信息：page-" + page);
//		return page;
//	}
//}
