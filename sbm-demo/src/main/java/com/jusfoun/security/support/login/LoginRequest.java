package com.jusfoun.security.support.login;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述 : 登录请求包装. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午2:53:15
 */
public class LoginRequest {
	private String username;
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