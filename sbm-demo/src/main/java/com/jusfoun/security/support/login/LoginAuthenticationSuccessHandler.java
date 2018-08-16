package com.jusfoun.security.support.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.entity.TokenUserDetails;
import com.jusfoun.security.ClientDetails;
import com.jusfoun.security.RawGrantedToken;
import com.jusfoun.security.support.token.factory.TokenFactory;
import com.jusfoun.service.TokenUserDetailsService;

/**
 * 描述 : 认证成功处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午2:52:27
 */
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final ObjectMapper objectMapper;
	private final TokenFactory tokenFactory;
	private final TokenUserDetailsService tokenUserDetailsService;

	public LoginAuthenticationSuccessHandler(final ObjectMapper objectMapper, final TokenFactory tokenFactory,
			final TokenUserDetailsService tokenUserService) {
		this.objectMapper = objectMapper;
		this.tokenFactory = tokenFactory;
		this.tokenUserDetailsService = tokenUserService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		LoginAuthenticationToken auth = (LoginAuthenticationToken) authentication;
		TokenUserDetails userDetails = (TokenUserDetails) auth.getPrincipal();
		ClientDetails clientDetails = auth.getClientDetails();

		/**
		 * 如果原来不存在token则生成token并保存到数据库中,<br>
		 * 如果库中有token记录并且token已经过期则只生成新的token并把信息更新到数据库中,<br>
		 * 如果库中已经有token并且没有过期直接放回token。<br>
		 */
		TokenUserDetails record = tokenUserDetailsService.findAndCacheByUsernameAndClientId(userDetails.getUsername(),
				clientDetails.getClientId());
		// 非单点登录
		/*
		 * if (record == null) { userDetails =
		 * TokenUserDetails.token(userDetails, tokenFactory, clientDetails);
		 * tokenUserDetailsService.insert(userDetails); } else if
		 * (record.hasAccessTokenExpired()) { userDetails =
		 * TokenUserDetails.token(userDetails, tokenFactory, clientDetails);
		 * userDetails.setId(record.getId());
		 * tokenUserDetailsService.updateById(userDetails); } else {
		 * record.setAuthorities(userDetails.getAuthorities());
		 * tokenUserDetailsService.updateById(record); userDetails = record; }
		 */

		// 单点登录
		userDetails = TokenUserDetails.token(userDetails, tokenFactory, clientDetails);
		if (record == null) {
			tokenUserDetailsService.insertAndCache(userDetails);
		} else {
			userDetails.setId(record.getId());
			tokenUserDetailsService.updateAndCache(userDetails);
		}

		// 将token返回给客户端
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		objectMapper.writeValue(response.getWriter(), BaseResponse.success(new RawGrantedToken(userDetails)));
		clearAuthenticationAttributes(request);
	}

	/**
	 * Removes temporary authentication-related data which may have been stored
	 * in the session during the authentication process..
	 */
	protected final void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
