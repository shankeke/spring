package com.shanke.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

public interface ShiroCacheManager extends CacheManager {
	<K, V> Cache<K, V> getCache(String name);

	void destroy();
}
