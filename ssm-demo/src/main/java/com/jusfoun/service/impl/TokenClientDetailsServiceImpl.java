package com.jusfoun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jusfoun.common.cache.CacheConsts;
import com.jusfoun.common.mybatis.mapper.MyBaseMapper;
import com.jusfoun.common.mybatis.mapper.MyIdableMapper;
import com.jusfoun.common.mybatis.mapper.extend.BaseWithAssociateSelectMapper;
import com.jusfoun.entity.TokenClientDetails;
import com.jusfoun.mapper.ds0.TokenClientDetailsMapper;
import com.jusfoun.service.TokenClientDetailsService;

@Service
public class TokenClientDetailsServiceImpl implements TokenClientDetailsService {

	@Autowired
	private TokenClientDetailsMapper tokenClientDetailsMapper;

	@Override
	public BaseWithAssociateSelectMapper<TokenClientDetails> getBaseWithAssociateSelectMapper() {
		return tokenClientDetailsMapper;
	}

	@Override
	public MyIdableMapper<TokenClientDetails> getMyIdableMapper() {
		return tokenClientDetailsMapper;
	}

	@Override
	public MyBaseMapper<TokenClientDetails> getMyBaseMapper() {
		return tokenClientDetailsMapper;
	}

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
