package com.jusfoun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jusfoun.common.cache.CacheConsts;
import com.jusfoun.common.mybatis.mapper.MyBaseMapper;
import com.jusfoun.common.mybatis.mapper.MyIdableMapper;
import com.jusfoun.common.mybatis.mapper.extension.BaseExtensionSelectMapper;
import com.jusfoun.common.utils.EntityUtils;
import com.jusfoun.entity.TokenUserDetails;
import com.jusfoun.mapper.ds0.TokenUserDetailsMapper;
import com.jusfoun.security.support.token.AccessToken;
import com.jusfoun.security.support.token.RefreshToken;
import com.jusfoun.service.TokenUserDetailsService;

/**
 * 说明： 用户toke信息管理. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月9日 上午11:37:10
 */
@Service
public class TokenUserDetailsServiceImpl implements TokenUserDetailsService {

	@Autowired
	private TokenUserDetailsMapper tokenUserDetailsMapper;

	@Override
	public BaseExtensionSelectMapper<TokenUserDetails> getBaseExtensionSelectMapper() {
		return tokenUserDetailsMapper;
	} 

	@Override
	public MyIdableMapper<TokenUserDetails> getMyIdableMapper() {
		return tokenUserDetailsMapper;
	}

	@Override
	public MyBaseMapper<TokenUserDetails> getMyBaseMapper() {
		return tokenUserDetailsMapper;
	}

	@CachePut(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_token_' + #record.clientId + '_' + #record.username", unless = "#result == null")
	@Override
	public TokenUserDetails insertAndCache(TokenUserDetails record) {
		insert(record);
		return record;
	}

	@CachePut(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_token_' + #record.clientId + '_' + #record.username", unless = "#result == null")
	@Override
	public TokenUserDetails updateAndCache(TokenUserDetails record) {
		updateByPrimaryKey(record);
		return record;
	}

	@CacheEvict(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_token_' + #clientId + '_'+ #username", beforeInvocation = false)
	@Override
	public int deleteWithCacheByUsernameAndClientId(String username, String clientId) {
		TokenUserDetails record = new TokenUserDetails();
		record.setUsername(username);
		record.setClientId(clientId);
		return delete(record);
	}

	@Cacheable(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_token_' + #clientId + '_' + #username", unless = "#result == null")
	@Override
	public TokenUserDetails findAndCacheByUsernameAndClientId(String username, String clientId) {
		TokenUserDetails record = new TokenUserDetails();
		record.setUsername(username);
		record.setClientId(clientId);
		return selectExtensionOne(EntityUtils.transBean2Map(record));
	}

	@Cacheable(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_token_' + #token.clientId + '_' + #token.subject", unless = "#result == null")
	@Override
	public TokenUserDetails findAndCacheByAccessToken(AccessToken token) {
		TokenUserDetails record = new TokenUserDetails();
		record.setAccessToken(token.getToken());
		return selectExtensionOne(EntityUtils.transBean2Map(record));
	}

	@Cacheable(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_token_' + #token.clientId + '_' + #token.subject", unless = "#result == null")
	@Override
	public TokenUserDetails findAndCacheByRefreshToken(RefreshToken token) {
		TokenUserDetails record = new TokenUserDetails();
		record.setRefreshToken(token.getToken());
		return selectExtensionOne(EntityUtils.transBean2Map(record));
	}
}
