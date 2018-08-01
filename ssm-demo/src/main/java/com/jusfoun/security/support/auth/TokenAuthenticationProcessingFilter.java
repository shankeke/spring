package com.jusfoun.security.support.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.jusfoun.security.config.WebSecurityConfig;
import com.jusfoun.security.exceptions.TokenInvalidException;
import com.jusfoun.security.support.token.AccessToken;
import com.jusfoun.security.support.token.extractor.TokenExtractorAdapter;
import com.jusfoun.security.support.token.factory.TokenFactory;

/**
 * 描述 : 鉴权过滤器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:04:19
 */
public class TokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

	private static final Logger log = LoggerFactory.getLogger(TokenAuthenticationProcessingFilter.class);

	private final AuthenticationFailureHandler failureHandler;
	private final TokenExtractorAdapter tokenExtractorAdapter;
	private final TokenFactory tokenFactory;

	public TokenAuthenticationProcessingFilter(RequestMatcher matcher, final AuthenticationFailureHandler failureHandler, final TokenExtractorAdapter tokenExtractorAdapter,
			final TokenFactory tokenFactory) {
		super(matcher);
		this.failureHandler = failureHandler;
		this.tokenExtractorAdapter = tokenExtractorAdapter;
		this.tokenFactory = tokenFactory;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		String tokenPayload = request.getHeader(WebSecurityConfig.TOKEN_HEADER_PARAM);
		if (log.isDebugEnabled()) {
			log.debug(String.format("Processing request '%s' with header token '%s' ", request.getServletPath(), tokenPayload));
		}
		tokenPayload = tokenExtractorAdapter.excute(tokenPayload);
		AccessToken token = null;
		try {
			token = tokenFactory.parseAccessToken(tokenPayload);
		} catch (TokenInvalidException e) {
			e.printStackTrace();
			throw new TokenInvalidException("Invalid token: " + tokenPayload);
		}
		return getAuthenticationManager().authenticate(new RawAuthenticationToken(token));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
			throws IOException, ServletException {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authResult);
		SecurityContextHolder.setContext(context);
		chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		failureHandler.onAuthenticationFailure(request, response, failed);
	}
}
