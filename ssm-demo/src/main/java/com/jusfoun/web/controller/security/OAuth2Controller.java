package com.jusfoun.web.controller.security;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jusfoun.common.annotation.Logable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 描述 : 授权管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月26日 下午1:52:50
 */
@Api(description = "认证信息获取接口类", value = "认证信息获取接口类")
@RestController
@RequestMapping("/auth")
@SessionAttributes("authorizationRequest")
public class OAuth2Controller {

	/**
	 * 描述 :获取用户信息. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月26日 下午1:52:04
	 * @param user
	 *            用户信息
	 * @return
	 */
	@ApiOperation(value = "获取登录用户信息", notes = "获取登录用户信息", hidden = false)
	@Logable(desc = "获取登录用户信息", fullPath = "获取登录用户信息")
	@RequestMapping(value = {"/user"}, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public Principal user(Principal user) {
		return user;
	}
}
