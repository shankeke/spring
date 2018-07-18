package com.shanke.dubbo.web.controller.test;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/test")
public class TestController {

	@RequestMapping(value = "/{day}", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult getForDay(
			@PathVariable @DateTimeFormat(iso = ISO.DATE) Date day) {
		return new BaseResult(ResultType.SUCC, day);
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult getNewForm() {
		return new BaseResult(ResultType.SUCC);
	}

	@RequestMapping(method = RequestMethod.POST)
	public BaseResult post(@Validated String str, BindingResult result) {
		return new BaseResult(ResultType.SUCC);
	}

	// , consumes = "application/json", params = "name=lisi", headers =
	// "Referer=http://www.ifeng.com/"
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public BaseResult test(
			@CookieValue("JSESSIONID") String cookie,//
			@RequestHeader("Accept-Encoding") String encoding, //
			@RequestHeader("Keep-Alive") long keepAlive,//
			@PathVariable String id,//
			@RequestParam(name = "name", required = true, defaultValue = "") String name//
	) {
		User user = new User("lisi", "123456", "12345678901", 11, "123@163.com");
		return new BaseResult(ResultType.SUCC, user);
	}
}
