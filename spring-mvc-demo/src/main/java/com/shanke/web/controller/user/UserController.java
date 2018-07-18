package com.shanke.web.controller.user;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shanke.entity.User;
import com.shanke.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Resource
	private UserService userService;

	@RequestMapping("/list")
	public String getUserList(Map<String, Object> model) {
		model.put("userList", userService.selectAll());
		return "user";
	}

	// http://demo/user/login/zhangsan/123456
	@RequestMapping("/edit/${groupid}/${userid}")
	@ResponseBody
	public User getUserList(@PathVariable(value = "groupid") int groupid,
			@PathVariable(value = "userid") int userid) {
		logger.info("------进入用户信息修改-------");
		// return "user_edit";
		return new User();
	}
}
