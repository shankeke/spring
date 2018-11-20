package com.jusfoun.security.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.log.Logable;
import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.entity.TokenUserDetails;
import com.jusfoun.security.config.WebSecurityConfig;
import com.jusfoun.security.support.token.factory.TokenFactory;
import com.jusfoun.service.TokenUserDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 说明：销毁令牌. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月9日 下午5:20:40
 */
@Api(tags = "AUTH-RevokeTokenEndpoint", value = "销毁令牌接口类", description = "销毁令牌接口类")
@RestController
public class RevokeTokenEndpoint {
	@Autowired
	private TokenUserDetailsService tokenUserDetailsService;
	@Autowired
	private TokenFactory tokenFactory;

	@ApiOperation(value = "销毁令牌", notes = "根据令牌信息删除认证信息", hidden = false)
	@Logable(path = "系统管理/用户管理/销毁令牌", value = "销毁令牌")
	@PostMapping({WebSecurityConfig.TOKEN_REVOKE_ENTRY_POINT})
	public BaseResponse<?> refreshToken(//
			@ApiParam(value = "令牌", required = true) @RequestHeader(name = WebSecurityConfig.TOKEN_HEADER_PARAM, required = true) String token //
	) {
		try {
			TokenUserDetails record = tokenUserDetailsService.findAndCacheByAccessToken(tokenFactory.parseAccessToken(token));
			if (record != null)
				tokenUserDetailsService.deleteWithCacheByUsernameAndClientId(record.getUsername(), record.getClientId());
			return BaseResponse.success();
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResponse.success();
		}
	}
}
