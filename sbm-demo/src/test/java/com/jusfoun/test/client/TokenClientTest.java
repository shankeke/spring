package com.jusfoun.test.client;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.jusfoun.security.config.WebSecurityConfig;

/**
 * 说明：用户授权接口测试. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月23日 下午6:12:47
 */
public class TokenClientTest extends BaseClient<JSONObject> {

	@Test
	public void token() {
		System.out.println("access_token: " + access_token);
		System.out.println("refresh_token: " + refresh_token);
	}

	@Test
	public void refreshToken() {
		headers.add("Authorization", "Bearer " + refresh_token);
		json(WebSecurityConfig.TOKEN_REFRESH_ENTRY_POINT, t);
	}

	@Test
	public void revokeToken() {
		json(WebSecurityConfig.TOKEN_REVOKE_ENTRY_POINT, t);
	}

	@Test
	public void user() {
		json("/auth/user", null);
	}
}
