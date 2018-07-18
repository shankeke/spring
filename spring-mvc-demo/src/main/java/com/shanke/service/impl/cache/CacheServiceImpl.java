package com.shanke.service.impl.cache;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.shanke.service.cache.CacheService;

/**
 * 描述 :缓存管理实现. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-12-21 下午2:02:06
 * @version 1.0
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {

	@Resource(name = "redisCacheManager")
	private CacheManager cacheManager;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.shanke.service.cache.CacheService#getCacheNames()
	 */
	@Override
	public Collection<String> getCacheNames() {
		return cacheManager.getCacheNames();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.shanke.service.cache.CacheService#put(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public void put(String cacheName, String key, String value)
			throws Exception {
		Cache cache = get(cacheName);
		if (cache != null)
			cache.put(key, value);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.shanke.service.cache.CacheService#put(java.lang.String,
	 *      java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(String cacheName, String key, Object value)
			throws Exception {
		Cache cache = get(cacheName);
		if (cache != null)
			cache.put(key, value);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.shanke.service.cache.CacheService#get(java.lang.String)
	 */
	@Override
	public Cache get(String cacheName) throws Exception {
		return cacheManager.getCache(cacheName);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.shanke.service.cache.CacheService#get(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public Object get(String cacheName, String key) throws Exception {
		Cache cache = get(cacheName);
		if (cache != null)
			return cache.get(key);
		return null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.shanke.service.cache.CacheService#evict(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void evict(String cacheName, String key) throws Exception {
		Cache cache = get(cacheName);
		if (cache != null)
			cache.evict(key);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.shanke.service.cache.CacheService#clear(java.lang.String)
	 */
	@Override
	public void clear(String cacheName) throws Exception {
		Cache cache = get(cacheName);
		if (cache != null)
			cache.clear();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.shanke.service.cache.CacheService#clear()
	 */
	@Override
	public void clear() throws Exception {
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
