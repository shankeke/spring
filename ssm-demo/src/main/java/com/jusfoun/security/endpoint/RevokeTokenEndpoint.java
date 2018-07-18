package com.jusfoun.security.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.annotation.Logable;
import com.jusfoun.common.result.BaseResponse;
import com.jusfoun.entity.TokenUserDetails;
import com.jusfoun.security.config.WebSecurityConfig;
import com.jusfoun.security.support.token.extractor.BearerHeaderTokenExtractor;
import com.jusfoun.security.support.token.factory.TokenFactory;
import com.jusfoun.service.TokenUserDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 描述 :销毁令牌. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月9日 下午5:20:40
 */
@Api(value = "销毁令牌接口类", description = "销毁令牌")
@RestController
public class RevokeTokenEndpoint {
	@Autowired
	private TokenUserDetailsService tokenUserDetailsService;
	@Autowired
	private BearerHeaderTokenExtractor tokenExtractor;
	@Autowired
	private TokenFactory tokenFactory;

	@ApiOperation(value = "销毁令牌", notes = "根据令牌信息删除认证信息", hidden = false)
	@Logable(fullPath = "系统管理/用户管理/销毁令牌", desc = "销毁令牌")
	@RequestMapping(value = {WebSecurityConfig.TOKEN_REVOKE_ENTRY_POINT}, method = {RequestMethod.POST, RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public BaseResponse<?> refreshToken(//
			@RequestHeader(name = WebSecurityConfig.TOKEN_HEADER_PARAM, required = true) String token //
	) {
		try {
			TokenUserDetails record = tokenUserDetailsService.findAndCacheByAccessToken(tokenFactory.parseAccessToken(tokenExtractor.extract(token)));
			if (record != null)
				tokenUserDetailsService.deleteWithCacheByUsernameAndClientId(record.getUsername(), record.getClientId());
			return BaseResponse.success();
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResponse.success();
		}
	}
}
