package com.jusfoun.security.support.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jusfoun.common.message.result.BaseResponse;
import com.jusfoun.security.exceptions.AuthenticationExceptionHandler;

/**
 * 描述 :自定义异常响应逻辑. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月3日 上午10:12:05
 */
@Component
public class RawBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
	private final ObjectMapper objectMapper;

	@Autowired
	public RawBasicAuthenticationEntryPoint(final ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException authException) throws IOException, ServletException {
		AuthenticationExceptionHandler handler = new AuthenticationExceptionHandler();
		BaseResponse<?> result = handler.handle(authException);
		// 设置response属性
		response.setStatus(result.getCode());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		// 向客户端写入消息体
		objectMapper.writeValue(response.getWriter(), result);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("Spring");
		super.afterPropertiesSet();
	}
}