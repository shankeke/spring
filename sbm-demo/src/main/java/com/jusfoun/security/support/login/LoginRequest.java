package com.jusfoun.security.support.login;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明： 登录请求包装. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午2:53:15
 */
@ApiModel
public class LoginRequest {
	
	@ApiModelProperty("用户名")
	private String username;
	
	@ApiModelProperty("用户密码")
	private String password;

	@JsonCreator
	public LoginRequest(@JsonProperty("username") String username, @JsonProperty("password") String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
