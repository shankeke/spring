package com.jusfoun.common.cache.service;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.jusfoun.common.message.exception.ServiceException;
import com.jusfoun.common.utils.ICollections;

/**
 * 描述 : 缓存管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月22日 上午9:51:19
 */
public interface CacheService {

	/**
	 * 描述:获取一个缓存管理器. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年9月6日 下午2:32:44
	 * @return 缓存管理器
	 */
	CacheManager getCacheManager();

	/**
	 * 描述 : 获取所有的缓存名称列表. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月22日 上午9:51:01
	 * @return 缓存名称列表
	 * @throws ServiceException
	 */
	default Collection<String> getCacheNames() throws ServiceException {
		return getCacheManager().getCacheNames();
	}

	/**
	 * 描述 : 向指定的缓存实例中存入键值. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月22日 上午9:50:30
	 * @param cacheName
	 *            缓存名称
	 * @param key
	 *            缓存键
	 * @param value
	 *            缓存值
	 * @throws ServiceException
	 */
	default void put(String cacheName, String key, String value) throws ServiceException {
		Cache cache = get(cacheName);
		if (cache != null) {
			cache.put(key, value);
		}
	}

	/**
	 * 描述 : 向指定的缓存实例中存入键值. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月22日 上午9:50:06
	 * @param cacheName
	 * @param key
	 * @param value
	 * @throws ServiceException
	 */
	default void put(String cacheName, String key, Object value) throws ServiceException {
		Cache cache = get(cacheName);
		if (cache != null) {
			cache.put(key, value);
		}
	}

	/**
	 * 描述 : 获取指定名称的缓存实例. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月22日 上午9:49:19
	 * @param cacheName
	 *            缓存名称
	 * @return
	 * @throws ServiceException
	 */
	default Cache get(String cacheName) throws ServiceException {
		return getCacheManager().getCache(cacheName);
	}

	/**
	 *
	 * 描述 : 获取缓存数据. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月22日 上午9:48:49
	 * @param cacheName
	 *            缓存实例名称
	 * @param key
	 *            缓存键
	 * @return
	 * @throws ServiceException
	 */
	default Object get(String cacheName, String key) throws ServiceException {
		Cache cache = get(cacheName);
		if (cache != null) {
			return cache.get(key);
		}
		return null;
	}

	/**
	 *
	 * 描述 : 删除缓存数据. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月22日 上午9:48:33
	 * @param cacheName
	 *            缓存实例名称
	 * @param key
	 *            缓存键
	 * @throws ServiceException
	 */
	default void evict(String cacheName, String key) throws ServiceException {
		Cache cache = get(cacheName);
		if (cache != null) {
			cache.evict(key);
		}
	}

	/**
	 * 描述 : 清空指定实例的缓存. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月22日 上午9:48:20
	 * @param cacheName
	 *            缓存实例名称
	 * @throws ServiceException
	 */
	default void clear(String cacheName) throws ServiceException {
		Cache cache = get(cacheName);
		if (cache != null) {
			cache.clear();
		}
	}

	/**
	 * 描述 :清空所有的缓存. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月22日 上午9:48:10
	 * @throws ServiceException
	 */
	default void clear() throws ServiceException {
		Collection<String> cacheNames = getCacheNames();
		if (ICollections.hasElements(cacheNames)) {
			Cache cache = null;
			for (String cacheName : cacheNames) {
				cache = get(cacheName);
				if (cache != null) {
					cache.clear();
				}
			}
		}
	}
}