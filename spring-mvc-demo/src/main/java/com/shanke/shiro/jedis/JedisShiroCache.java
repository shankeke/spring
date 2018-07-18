package com.shanke.shiro.jedis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;

import com.shanke.shiro.cache.CacheConstant;
import com.shanke.shiro.utils.SerializeUtils;

public class JedisShiroCache<K, V> implements Cache<K, V> {

	/**
	 * The wrapped Jedis instance.
	 */
	private JedisManager jedisManager;

	/**
	 * The Redis key prefix for the sessions
	 */
	private String keyPrefix = CacheConstant.CACHE_KEY_PREFIX;

	/**
	 * Returns the Redis session keys prefix.
	 * 
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key prefix.
	 * 
	 * @param keyPrefix
	 *            The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	private String name;

	public String getName() {
		if (name == null) {
			return "";
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 通过一个JedisManager实例构造RedisCache
	 */
	public JedisShiroCache(JedisManager jedisManager) {
		if (jedisManager == null) {
			throw new IllegalArgumentException("Cache argument cannot be null.");
		}
		this.jedisManager = jedisManager;
	}

	/**
	 * Constructs a cache instance with the specified Redis manager and using a
	 * custom key prefix.
	 * 
	 * @param cache
	 *            The cache manager instance
	 * @param name
	 *            The cache name
	 */
	public JedisShiroCache(JedisManager jedisManager, String name) {
		this(jedisManager);
		this.name = name;

		// 通过反射得到T的真实类型
		/*
		 * Class<K> clazz = GenericsUtils.getSuperClassGenricType(getClass(),
		 * 0); Class<K> clazz1 =
		 * GenericsUtils.getSuperClassGenricType(getClass(), 0);
		 * logger.debug("JedisShiroCache name:" + name + ", K:" +
		 * clazz.getName() + ", V:" + clazz1.getName());
		 */

	}

	/**
	 * Constructs a cache instance with the specified Redis manager and using a
	 * custom key prefix.
	 * 
	 * @param cache
	 *            The cache manager instance
	 * @param name
	 *            The cache name
	 * @param keyPrefix
	 *            Redis key prefix
	 */
	public JedisShiroCache(JedisManager jedisManager, String name,
			String keyPrefix) {
		this(jedisManager, name);
		this.keyPrefix = keyPrefix;
	}

	/**
	 * 获得byte[]型的key
	 * 
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(K key) {
		if (key instanceof String) {
			String preKey = this.name + this.keyPrefix + key;
			// return preKey.getBytes();
			return SerializeUtils.serialize(preKey);
		} else {
			return SerializeUtils.serialize(key);
		}
	}

	@Override
	public V get(K key) throws CacheException {
		try {
			if (key == null) {
				return null;
			} else {
				byte[] rawValue = jedisManager.get(getByteKey(key));
				if (rawValue == null) {
					return null;
				}
				@SuppressWarnings("unchecked")
				V value = (V) SerializeUtils.deserialize(rawValue);
				return value;
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V put(K key, V value) throws CacheException {
		try {
			jedisManager.set(getByteKey(key), SerializeUtils.serialize(value));
			return value;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public V remove(K key) throws CacheException {
		try {
			V previous = get(key);
			jedisManager.del(getByteKey(key));
			return previous;
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public void clear() throws CacheException {
		try {
			jedisManager.flushDB();
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public int size() {
		try {
			Long longSize = new Long(jedisManager.dbSize());
			return longSize.intValue();
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public Set<K> keys() { try { Set<byte[]> keys =
	 * jedisManager.keys(this.keyPrefix + "*"); if
	 * (CollectionUtils.isEmpty(keys)) { return Collections.emptySet(); } else {
	 * Set<K> newKeys = new HashSet<K>(); for (byte[] key : keys) { //
	 * newKeys.add((K) key); newKeys.add((K) SerializeUtils.deserialize(key)); }
	 * return newKeys; } } catch (Throwable t) { throw new CacheException(t); }
	 * }
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public Collection<V> values() { try { Set<byte[]> keys =
	 * jedisManager.keys(this.keyPrefix + "*"); if
	 * (!CollectionUtils.isEmpty(keys)) { List<V> values = new
	 * ArrayList<V>(keys.size()); for (byte[] key : keys) { V value = get((K)
	 * key); if (value != null) { // values.add(value); values.add((V)
	 * SerializeUtils.deserialize(jedisManager .get(key))); } } return
	 * Collections.unmodifiableList(values); } else { return
	 * Collections.emptyList(); } } catch (Throwable t) { throw new
	 * CacheException(t); } }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		try {
			Set<byte[]> keys = jedisManager.keys(this.name + this.keyPrefix
					+ "*");
			if (CollectionUtils.isEmpty(keys)) {
				return Collections.emptySet();
			} else {
				Set<K> newKeys = new HashSet<K>();
				for (byte[] key : keys) {
					newKeys.add((K) key);
				}
				return newKeys;
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

	@Override
	public Collection<V> values() {
		try {
			Set<byte[]> keys = jedisManager.keys(this.name + this.keyPrefix
					+ "*");
			if (!CollectionUtils.isEmpty(keys)) {
				List<V> values = new ArrayList<V>(keys.size());
				for (byte[] key : keys) {
					@SuppressWarnings("unchecked")
					V value = get((K) key);
					if (value != null) {
						values.add(value);
					}
				}
				return Collections.unmodifiableList(values);
			} else {
				return Collections.emptyList();
			}
		} catch (Throwable t) {
			throw new CacheException(t);
		}
	}

}
