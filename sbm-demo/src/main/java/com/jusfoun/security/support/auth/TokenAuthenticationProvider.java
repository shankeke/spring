package com.jusfoun.security.support.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.jusfoun.entity.TokenUserDetails;
import com.jusfoun.security.exceptions.TokenExpiredException;
import com.jusfoun.security.support.token.AccessToken;
import com.jusfoun.service.TokenUserDetailsService;

/**
 * 说明： 鉴权处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:02:57
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
	
	private final TokenUserDetailsService tokenUserDetailsService;

	@Autowired
	public TokenAuthenticationProvider(TokenUserDetailsService tokenUserDetailsService) {
		this.tokenUserDetailsService = tokenUserDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		AccessToken accessToken = (AccessToken) authentication.getCredentials();
		TokenUserDetails userDetails = tokenUserDetailsService.findAndCacheByAccessToken(accessToken);
		// 没有查询到token信息，抛出token无效异常
		if (userDetails == null) {
			throw new BadCredentialsException("Invalid token: " + accessToken.getToken());
		}
		// token过期，删除token
		if (userDetails.hasAccessTokenExpired()) {
			tokenUserDetailsService.deleteWithCacheByUsernameAndClientId(userDetails.getUsername(), "");
			throw new TokenExpiredException("Expired token: " + accessToken.getToken());
		}
		// 返回token信息继续请求
		return new RawAuthenticationToken(new TokenUserDetails(userDetails.getUsername(), userDetails.getAuthorities()),
				userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (RawAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
