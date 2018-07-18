package com.shanke.shiro.jedis;

import java.util.Iterator;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.shanke.shiro.utils.SerializeUtils;

/**
 * 描述 :主要用来给用户提供一个设计完备的，通过jedis的jar包来管理redis内存数据库的各种方法. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-8-26 下午5:56:08
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class JedisManager {

	// 设置为0的话就是永远都不会过期
	private int expire = 0;

	private RedisTemplate redisTemplate;

	public JedisManager() {
	}

	public JedisManager(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public JedisManager(int expire, RedisTemplate redisTemplate) {
		this.expire = expire;
		this.redisTemplate = redisTemplate;
	}

	/**
	 * get value from redis
	 * 
	 * @param key
	 * @return
	 */
	public byte[] get(final byte[] key) {
		return (byte[]) redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.get(key);
			}

		});
	}

	/**
	 * get value from redis
	 * 
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		return (String) redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.get(SerializeUtils.serialize(key));
			}
		});
	}

	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public byte[] set(final byte[] key, final byte[] value) {
		return (byte[]) redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(key, value);
				if (expire != 0) {
					connection.expire(key, expire);
				}
				return value;
			}
		});
	}

	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @return
	 */

	public String set(final String key, final String value) {
		return (String) redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(SerializeUtils.serialize(key), value.getBytes());
				if (expire != 0) {
					connection.expire(SerializeUtils.serialize(key), expire);
				}
				return value;
			}
		});
	}

	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public byte[] set(final byte[] key, final byte[] value, final int expire) {
		return (byte[]) redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(key, value);
				if (expire != 0) {
					connection.expire(key, expire);
				}
				return value;
			}
		});
	}

	/**
	 * set
	 * 
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 */
	public String set(final String key, final String value, final int expire) {
		return (String) redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(SerializeUtils.serialize(key), value.getBytes());
				if (expire != 0) {
					connection.expire(SerializeUtils.serialize(key), expire);
				}
				return value;
			}
		});
	}

	/**
	 * del
	 * 
	 * @param key
	 */
	public void del(final byte[] key) {
		redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.del(key);
			}
		});
	}

	/**
	 * del
	 * 
	 * @param key
	 */
	public void del(final String key) {
		redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.del(SerializeUtils.serialize(key));
			}
		});
	}

	/**
	 * flush
	 */
	public void flushDB() {
		redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.flushDb();
				return connection;
			}
		});
	}

	/**
	 * size
	 */
	public Long dbSize() {
		return (Long) redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.dbSize();
			}
		});
	}

	/**
	 * keys
	 * 
	 * @param regex
	 * @return
	 */
	public Set<byte[]> keys(final String pattern) {
		return (Set<byte[]>) redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				return connection.keys(SerializeUtils.serialize(pattern));
			}
		});
	}

	public void dels(final String pattern) {
		redisTemplate.execute(new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				Set<byte[]> keys = connection.keys(SerializeUtils
						.serialize(pattern));
				Iterator<byte[]> ito = keys.iterator();
				while (ito.hasNext()) {
					connection.del(ito.next());
				}
				return connection;
			}
		});
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
