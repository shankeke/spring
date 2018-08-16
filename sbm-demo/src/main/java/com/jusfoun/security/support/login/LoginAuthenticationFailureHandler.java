package com.jusfoun.security.support.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.security.exceptions.AuthenticationExceptionHandler;

/**
 * 描述 :认证失败处理器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午2:52:04
 */
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private final ObjectMapper objectMapper;

	public LoginAuthenticationFailureHandler(final ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
		AuthenticationExceptionHandler handler = new AuthenticationExceptionHandler();
		BaseResponse<?> result = handler.handle(e);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		objectMapper.writeValue(response.getWriter(), result);
	}
}
