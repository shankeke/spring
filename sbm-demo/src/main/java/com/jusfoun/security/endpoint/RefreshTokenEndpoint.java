package com.jusfoun.security.endpoint;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.common.log.Logable;
import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.entity.TokenUserDetails;
import com.jusfoun.security.ClientDetails;
import com.jusfoun.security.ClientDetailsService;
import com.jusfoun.security.RawGrantedToken;
import com.jusfoun.security.config.WebSecurityConfig;
import com.jusfoun.security.exceptions.ClientIdNotFoundException;
import com.jusfoun.security.exceptions.NoGrantedAnyAuthorityException;
import com.jusfoun.security.exceptions.TokenExpiredException;
import com.jusfoun.security.exceptions.TokenInvalidException;
import com.jusfoun.security.support.token.RefreshToken;
import com.jusfoun.security.support.token.factory.TokenFactory;
import com.jusfoun.service.TokenUserDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 说明：刷新令牌. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:09:50
 */
@Api(tags = "AUTH-RefreshTokenEndpoint", value = "刷新令牌接口类", description = "刷新令牌接口类")
@RestController
public class RefreshTokenEndpoint {

	@Autowired
	private TokenFactory tokenFactory;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenUserDetailsService tokenUserDetailsService;
	@Autowired
	private ClientDetailsService clientDetailsService;

	@ApiOperation(value = "刷新令牌", notes = "根据刷新令牌获取新的令牌信息", hidden = false)
	@Logable(path = "系统管理/用户管理/刷新令牌", value = "刷新令牌")
	@PostMapping(WebSecurityConfig.TOKEN_REFRESH_ENTRY_POINT)
	public BaseResponse<RawGrantedToken> refreshToken(//
			@ApiParam(value = "令牌", required = true) @RequestHeader(name = WebSecurityConfig.TOKEN_HEADER_PARAM, required = true) String token //
	) {
		// 解析token为RefreshToken对象
		RefreshToken refreshToken = tokenFactory.parseRefreshToken(token);
		// 查询用户登录记录
		TokenUserDetails tokenDetails = tokenUserDetailsService.findAndCacheByRefreshToken(refreshToken);
		if (tokenDetails == null) {
			throw new TokenInvalidException("Invalid Token: " + token);
		}
		// 判断token是否过期
		if (tokenDetails.hasRefreshTokenExpired()) {
			throw new TokenExpiredException("Expired Token: " + token);
		}

		// 查询客户端信息
		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(tokenDetails.getClientId());
		if (clientDetails == null) {
			throw new ClientIdNotFoundException("ClientId not found !");
		}

		// 创建新的token信息并更新数据库
		TokenUserDetails userDetails = (TokenUserDetails) userDetailsService.loadUserByUsername(tokenDetails.getUsername());
		if (userDetails == null) {
			throw new UsernameNotFoundException("Username not found !");
		}

		// 重新构建权限列表
		Collection<? extends GrantedAuthority> retainAuthorities = clientDetails.retainAuthorities(userDetails.getAuthorities());
		if (retainAuthorities == null || retainAuthorities.isEmpty()) {
			throw new NoGrantedAnyAuthorityException("User has no authority assigned !");
		}
		userDetails.setAuthorities(retainAuthorities);
		userDetails = TokenUserDetails.token(userDetails, tokenFactory, clientDetails);

		// 将新的token信息入库
		tokenDetails.setId(tokenDetails.getId());
		tokenUserDetailsService.updateAndCache(tokenDetails);
		return BaseResponse.success(new RawGrantedToken(tokenDetails));
	}
}