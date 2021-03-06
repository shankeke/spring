package com.jusfoun.common.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * 说明： 缓存管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月22日 上午9:53:00
 */
@Service
public class CacheServiceImpl implements CacheService {

	@Autowired
	private CacheManager cacheManager;

	@Override
	public CacheManager getCacheManager() {
		return cacheManager;
	}
}
