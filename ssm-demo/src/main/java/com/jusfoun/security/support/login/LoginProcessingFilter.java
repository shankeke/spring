package com.jusfoun.security.support.login;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jusfoun.entity.TokenClientDetails;
import com.jusfoun.security.config.WebSecurityConfig;
import com.jusfoun.security.exceptions.AuthMethodNotSupportedException;
import com.jusfoun.security.exceptions.ClientException;
import com.jusfoun.security.support.token.extractor.TokenExtractorAdapter;
import com.jusfoun.security.util.WebUtil;

/**
 * 描述 : 登录流程过滤器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午2:52:47
 */
public class LoginProcessingFilter extends AbstractAuthenticationProcessingFilter {
	private static Logger log = LoggerFactory.getLogger(LoginProcessingFilter.class);

	private final ObjectMapper objectMapper;
	private final TokenExtractorAdapter tokenExtractorAdapter;
	private final AuthenticationSuccessHandler successHandler;
	private final AuthenticationFailureHandler failureHandler;

	public LoginProcessingFilter(String defaultProcessUrl, final AuthenticationSuccessHandler successHandler, final AuthenticationFailureHandler failureHandler,
			final ObjectMapper objectMapper, final TokenExtractorAdapter tokenExtractorAdapter) {
		super(defaultProcessUrl);
		this.successHandler = successHandler;
		this.failureHandler = failureHandler;
		this.objectMapper = objectMapper;
		this.tokenExtractorAdapter = tokenExtractorAdapter;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		if (!HttpMethod.POST.name().equals(request.getMethod()) || !WebUtil.isAjax(request)) {
			if (log.isDebugEnabled()) {
				log.debug("Authentication method not supported. Request method: " + request.getMethod());
			}
			throw new AuthMethodNotSupportedException("Authentication method not supported");
		}
		// 验证客户端信息
		String tokenPayload = request.getHeader(WebSecurityConfig.TOKEN_HEADER_PARAM);
		if (StringUtils.isEmpty(tokenPayload)) {
			throw new ClientException("ClientId and ClientSecret not provided !");
		}
		tokenPayload = tokenExtractorAdapter.excute(tokenPayload);

		// 验证用户信息
		LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);
		if (StringUtils.isBlank(loginRequest.getUsername()) || StringUtils.isBlank(loginRequest.getPassword())) {
			throw new AuthenticationServiceException("Username or Password not provided !");
		}

		String[] clientInfo = tokenPayload.split(":");
		LoginAuthenticationToken token = new LoginAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword(),
				new TokenClientDetails(clientInfo[0], tokenPayload.split(":")[1]));
		return this.getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
			throws IOException, ServletException {
		successHandler.onAuthenticationSuccess(request, response, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		failureHandler.onAuthenticationFailure(request, response, failed);
	}
}
