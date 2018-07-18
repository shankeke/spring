package com.shanke.shiro.jedis;

import org.apache.shiro.cache.Cache;

import com.shanke.shiro.cache.ShiroCacheManager;

public class JedisShiroCacheManager implements ShiroCacheManager {

	private JedisManager jedisManager;

	public JedisManager getJedisManager() {
		return jedisManager;
	}

	public void setJedisManager(JedisManager jedisManager) {
		this.jedisManager = jedisManager;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) {
		return new JedisShiroCache<K, V>(jedisManager, name);
	}

	@Override
	public void destroy() {
		jedisManager.flushDB();
	}
}
