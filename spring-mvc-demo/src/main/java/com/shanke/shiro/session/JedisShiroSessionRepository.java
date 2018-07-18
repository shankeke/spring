package com.shanke.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;

import com.shanke.shiro.cache.CacheConstant;
import com.shanke.shiro.jedis.JedisManager;
import com.shanke.shiro.utils.SerializeUtils;

public class JedisShiroSessionRepository implements ShiroSessionRepository {

	/** redis session key 前缀 **/
	private final String REDIS_SHIRO_SESSION = CacheConstant.CACHE_KEY_PREFIX;

	private JedisManager jedisManager;

	@Override
	public void saveSession(Session session) {
		if (session == null || session.getId() == null) {
			System.out.println("session 或者 session ID 为空");
		}
		byte[] key = SerializeUtils.serialize(getRedisSessionKey(session
				.getId()));
		byte[] value = SerializeUtils.serialize(session);

		Long timeOut = session.getTimeout() / 1000;
		jedisManager.set(key, value, Integer.parseInt(timeOut.toString()));

	}

	@Override
	public void deleteSession(Serializable sessionId) {
		if (sessionId == null) {
			System.out.println("id为空");
		}
		jedisManager.del(SerializeUtils
				.serialize(getRedisSessionKey(sessionId)));

	}

	@Override
	public Session getSession(Serializable sessionId) {
		if (null == sessionId) {
			System.out.println("id为空");
			return null;
		}
		Session session = null;
		byte[] value = jedisManager.get(SerializeUtils
				.serialize(getRedisSessionKey(sessionId)));
		if (null == value)
			return null;
		session = (Session) SerializeUtils.deserialize(value);
		return session;
	}

	@Override
	public Collection<Session> getAllSessions() {
		Set<Session> sessions = new HashSet<Session>();
		Set<byte[]> byteKeys = jedisManager
				.keys(this.REDIS_SHIRO_SESSION + "*");
		if (byteKeys != null && byteKeys.size() > 0) {
			for (byte[] bs : byteKeys) {
				Session s = (Session) SerializeUtils.deserialize(jedisManager
						.get(bs));
				sessions.add(s);
			}
		}
		return sessions;
	}

	/**
	 * 获取redis中的session key
	 * 
	 * @param sessionId
	 * @return
	 */
	private String getRedisSessionKey(Serializable sessionId) {
		return this.REDIS_SHIRO_SESSION + sessionId;
	}

	public JedisManager getJedisManager() {
		return jedisManager;
	}

	public void setJedisManager(JedisManager jedisManager) {
		this.jedisManager = jedisManager;
	}

	public JedisShiroSessionRepository() {

	}

	// public static void main(String[] args) {
	// Jedis jj = new Jedis("localhost");
	// //jj.set("key2", "232323231=========");
	// String ss = jj.get("key1");
	// System.out.println(jj.get("key2"));
	// System.out.println(ss);
	// }
}
