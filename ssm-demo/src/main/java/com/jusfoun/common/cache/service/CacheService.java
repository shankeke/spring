package com.jusfoun.common.cache.service;

import java.util.Collection;

import org.springframework.cache.Cache;

import com.jusfoun.common.exception.ServiceException;

/**
 * 描述 : 缓存管理. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月22日 上午9:51:19
 */
public interface CacheService {

	/**
	 * 描述 : 获取所有的缓存名称列表. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月22日 上午9:51:01
	 * @return 缓存名称列表
	 * @throws ServiceException
	 */
	Collection<String> getCacheNames() throws ServiceException;

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
	void put(String cacheName, String key, String value) throws ServiceException;

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
	void put(String cacheName, String key, Object value) throws ServiceException;

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
	Cache get(String cacheName) throws ServiceException;

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
	Object get(String cacheName, String key) throws ServiceException;

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
	void evict(String cacheName, String key) throws ServiceException;

	/**
	 * 描述 : 清空指定实例的缓存. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月22日 上午9:48:20
	 * @param cacheName
	 *            缓存实例名称
	 * @throws ServiceException
	 */
	void clear(String cacheName) throws ServiceException;

	/**
	 * 描述 :清空所有的缓存. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年9月22日 上午9:48:10
	 * @throws ServiceException
	 */
	void clear() throws ServiceException;

}