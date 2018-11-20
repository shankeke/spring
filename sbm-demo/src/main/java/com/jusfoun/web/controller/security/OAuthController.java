package com.jusfoun.web.controller.security;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jusfoun.common.log.Logable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 说明： 授权管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月26日 下午1:52:50
 */
@Api(tags = "AUTH-OAuthController", description = "获取认证信息接口类", value = "获取认证信息接口类")
@RestController
@RequestMapping(value = {"/auth"})
@SessionAttributes("authorizationRequest")
public class OAuthController {

	/**
	 * 说明：获取用户信息. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月26日 下午1:52:04
	 * @param user
	 *            用户信息
	 * @return
	 */
	@ApiOperation(value = "获取登录用户信息", notes = "获取登录用户信息", hidden = false)
	@Logable(value = "获取登录用户信息", path = "获取登录用户信息")
	@GetMapping({"/user"})
	@ResponseStatus(HttpStatus.OK)
	public Principal user(Principal user) {
		return user;
	}
}
