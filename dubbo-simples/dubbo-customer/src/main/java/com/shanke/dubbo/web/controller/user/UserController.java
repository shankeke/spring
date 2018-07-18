package com.shanke.dubbo.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shanke.dubbo.comm.annotation.Logable;
import com.shanke.dubbo.comm.annotation.Logable.LevelType;
import com.shanke.dubbo.comm.result.BaseResult;
import com.shanke.dubbo.comm.result.BaseResult.ResultType;
import com.shanke.dubbo.entity.User;
import com.shanke.dubbo.service.UserService;

@Logable(abled = true, level = LevelType.INFO)
@Controller
@RequestMapping("/user")
public class UserController {

	@Reference
	private UserService userService;

	@RequestMapping("/getUserById")
	@ResponseBody
	public BaseResult getUserById(
			@RequestParam(name = "id", required = true) Long id) {
		User user = userService.selectByPrimaryKey(id);
		BaseResult result = new BaseResult(ResultType.SUCC, user);
		return result;
	}
}
