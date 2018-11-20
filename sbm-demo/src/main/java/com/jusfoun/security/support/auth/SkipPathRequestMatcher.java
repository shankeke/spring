package com.jusfoun.security.support.auth;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

/**
 * 说明： 路径匹配器. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月8日 下午3:04:55
 */
public class SkipPathRequestMatcher implements RequestMatcher {
	private OrRequestMatcher matchers;
	private RequestMatcher processingMatcher;

	public SkipPathRequestMatcher(List<String> pathsToSkip, String processingPath) {
		Assert.notNull(pathsToSkip, "Parameter 'pathsToSkip' must be not null !");
		List<RequestMatcher> requestMatchers = pathsToSkip.parallelStream().map(path -> new AntPathRequestMatcher(path)).collect(Collectors.toList());
		matchers = new OrRequestMatcher(requestMatchers);
		processingMatcher = new AntPathRequestMatcher(processingPath);
	}

	@Override
	public boolean matches(HttpServletRequest request) {
		if (matchers.matches(request)) {
			return false;
		}
		return processingMatcher.matches(request);
	}
}
