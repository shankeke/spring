package com.jusfoun.client;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.jusfoun.security.config.WebSecurityConfig;

/**
 * 描述 :用户授权接口测试. <br>
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
		// rest(WebSecurityConfig.TOKEN_REFRESH_ENTRY_POINT, t, "Bearer " +
		// refresh_token);
	}

	@Test
	public void revokeToken() {
		rest(WebSecurityConfig.TOKEN_REVOKE_ENTRY_POINT, t);
	}

	@Test
	public void user() {
		rest("/auth/user", null);
	}
}
