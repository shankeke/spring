package com.shanke.dubbo.service;

import java.util.Collection;

import org.springframework.cache.Cache;

public interface CacheService {

	Collection<String> getCacheNames();

	void put(String cacheName, String key, String value) throws Exception;

	void put(String cacheName, String key, Object value) throws Exception;

	Cache get(String cacheName) throws Exception;

	Object get(String cacheName, String key) throws Exception;

	void evict(String cacheName, String key) throws Exception;

	void clear(String cacheName) throws Exception;

	void clear() throws Exception;

}