package com.shanke.web.controller.test;

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

import com.shanke.common.annotation.Logable;
import com.shanke.common.annotation.Logable.LevelType;
import com.shanke.common.result.BaseResult;
import com.shanke.common.result.BaseResult.ResultType;
import com.shanke.entity.User;

@Logable(enable = true, level = LevelType.INFO)
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
