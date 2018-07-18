package com.jusfoun.common.base.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.jusfoun.common.base.service.CacheService;
import com.jusfoun.common.exception.ServiceException;

/**
 * 描述 : 缓存管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月22日 上午9:53:00
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {

	@Autowired
	private CacheManager cacheManager;

	@Override
	public Collection<String> getCacheNames() throws ServiceException {
		return cacheManager.getCacheNames();
	}

	@Override
	public void put(String cacheName, String key, String value) throws ServiceException {
		Cache cache = get(cacheName);
		if (cache != null)
			cache.put(key, value);
	}

	@Override
	public void put(String cacheName, String key, Object value) throws ServiceException {
		Cache cache = get(cacheName);
		if (cache != null)
			cache.put(key, value);
	}

	@Override
	public Cache get(String cacheName) throws ServiceException {
		return cacheManager.getCache(cacheName);
	}

	@Override
	public Object get(String cacheName, String key) throws ServiceException {
		Cache cache = get(cacheName);
		if (cache != null)
			return cache.get(key);
		return null;
	}

	@Override
	public void evict(String cacheName, String key) throws ServiceException {
		Cache cache = get(cacheName);
		if (cache != null)
			cache.evict(key);
	}

	@Override
	public void clear(String cacheName) throws ServiceException {
		Cache cache = get(cacheName);
		if (cache != null)
			cache.clear();
	}

	@Override
	public void clear() throws ServiceException {
		Collection<String> cacheNames = getCacheNames();
		if (cacheNames != null && cacheNames.size() > 0) {
			for (String cacheName : cacheNames) {
				Cache cache = get(cacheName);
				if (cache != null)
					cache.clear();
			}
		}
	}
}
