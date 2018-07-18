package com.jusfoun.service.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jusfoun.common.base.service.impl.BaseWithAssociateServiceImpl;
import com.jusfoun.common.cache.CacheConsts;
import com.jusfoun.entity.TokenClientDetails;
import com.jusfoun.service.TokenClientDetailsService;

@Service
public class TokenClientDetailsServiceImpl extends BaseWithAssociateServiceImpl<TokenClientDetails> implements TokenClientDetailsService {

	@CachePut(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_client_' + #record.clientId", unless = "#result == null")
	@Override
	public TokenClientDetails insertAndCache(TokenClientDetails record) {
		insert(record);
		return record;
	}

	@CachePut(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_client_' + #record.clientId", unless = "#result == null")
	@Override
	public TokenClientDetails replaceAndCache(TokenClientDetails record) {
		replace(record);
		return record;
	}

	@CacheEvict(value = CacheConsts.CACHE_SECURITY, allEntries = true)
	@Override
	public int deleteWithCache(TokenClientDetails record) {
		return delete(record);
	}

	@CachePut(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_client_' + #record.clientId", unless = "#result == null")
	@Override
	public TokenClientDetails updateAndCache(TokenClientDetails record) {
		updateByPrimaryKey(record);
		return record;
	}

	@Cacheable(value = CacheConsts.CACHE_SECURITY, key = "'security_cache_client_' + #clientId", unless = "#result == null")
	@Override
	public TokenClientDetails findAndCacheByClientId(String clientId) {
		return selectPKWithAssociate("clientId", clientId);
	}

}
