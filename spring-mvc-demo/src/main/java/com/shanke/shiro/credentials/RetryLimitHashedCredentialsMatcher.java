package com.shanke.shiro.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述 : 密码尝试次数限制机制. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-8-26 下午2:59:31
 */
public class RetryLimitHashedCredentialsMatcher extends
		HashedCredentialsMatcher {
	private static final Logger logger = LoggerFactory
			.getLogger(RetryLimitHashedCredentialsMatcher.class);

	private Cache<String, AtomicInteger> passwordRetryCache;

	public static final String PWDRETRY_CACHE = "passwordRetryCache";

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache(PWDRETRY_CACHE);
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		// retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(username);

		if (retryCount == null)
			retryCount = new AtomicInteger(0);

		int incrementAndGet = retryCount.incrementAndGet();
		passwordRetryCache.put(username, retryCount);

		logger.debug("Password retry times: username [" + username
				+ "], retry times [" + retryCount.intValue() + "]");

		if (incrementAndGet > 5) {
			// if retry count > 5 throw
			throw new ExcessiveAttemptsException();
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry count
			passwordRetryCache.remove(username);
		}
		return matches;
	}
}