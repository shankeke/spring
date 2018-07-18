package com.shanke.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

public class CustomShiroCacheManager implements CacheManager, Destroyable {

	private ShiroCacheManager shrioCacheManager;

	public ShiroCacheManager getShrioCacheManager() {
		return shrioCacheManager;
	}

	public void setShrioCacheManager(ShiroCacheManager shrioCacheManager) {
		this.shrioCacheManager = shrioCacheManager;
	}

	@Override
	public void destroy() throws Exception {
		getShrioCacheManager().destroy();
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return getShrioCacheManager().getCache(name);
	}

}