package com.jusfoun.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jusfoun.common.base.service.impl.BaseWithAssociateServiceImpl;
import com.jusfoun.common.cache.CacheConsts;
import com.jusfoun.common.util.entry.EntityUtils;
import com.jusfoun.entity.TokenUserDetails;
import com.jusfoun.security.support.token.AccessToken;
import com.jusfoun.security.support.token.RefreshToken;
import com.jusfoun.service.TokenUserDetailsService;

/**
 * 描述 : 用户toke信息管理. <br>
 * 
 * @author yjw@jusfoun.com
 * @date 2017年11月9日 上午11:37:10
 */
@Service
public class TokenUserDetailsServiceImpl extends BaseWithAssociateServiceImpl<TokenUserDetails> implements TokenUserDetailsService {

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
		return selectOneWithAssociate(EntityUtils.transBean2Map(record));
	}

	@Cacheable(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_token_' + #token.clientId + '_' + #token.subject", unless = "#result == null")
	@Override
	public TokenUserDetails findAndCacheByAccessToken(AccessToken token) {
		TokenUserDetails record = new TokenUserDetails();
		record.setAccessToken(token.getToken());
		return selectOneWithAssociate(EntityUtils.transBean2Map(record));
	}

	@Cacheable(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_token_' + #token.clientId + '_' + #token.subject", unless = "#result == null")
	@Override
	public TokenUserDetails findAndCacheByRefreshToken(RefreshToken token) {
		TokenUserDetails record = new TokenUserDetails();
		record.setRefreshToken(token.getToken());
		return selectOneWithAssociate(EntityUtils.transBean2Map(record));
	}
}
