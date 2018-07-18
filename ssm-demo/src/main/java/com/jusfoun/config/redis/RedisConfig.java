//package com.jusfoun.config.redis;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
//import org.springframework.data.redis.connection.RedisSentinelConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
//import com.wsbs.common.cache.CustomRedisCacheManager;
//
//import redis.clients.jedis.JedisPoolConfig;
//
//@Configuration
//@EnableCaching 
//@EnableAutoConfiguration
//public class RedisConfig {
//
//	private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);
//
//	@Bean
//	@ConfigurationProperties(prefix = "spring.redis")
//	public JedisPoolConfig getJedisPoolConfig() {
//		return new JedisPoolConfig();
//	}
//
//	@Bean
//	@ConfigurationProperties(prefix = "spring.redis.sentinel")
//	public RedisSentinelConfiguration getRedisSentinelConfiguration() {
//		return new RedisSentinelConfiguration();
//	}
//
//	@Bean
//	@ConfigurationProperties(prefix = "spring.redis")
//	public JedisConnectionFactory getConnectionFactory() {
//		JedisConnectionFactory factory = new JedisConnectionFactory();
//		factory.setPoolConfig(getJedisPoolConfig());
//		logger.info("JedisConnectionFactory bean init success.");
//		return factory;
//	}
//
//	@Bean
//	public RedisTemplate<?, ?> getRedisTemplate() {
//		return new StringRedisTemplate(getConnectionFactory());
//	}
//
//	@Bean
//	public CacheManager getCacheManager(RedisTemplate<?, ?> redisTemplate) {
//		CustomRedisCacheManager cacheManager = new CustomRedisCacheManager(redisTemplate);
//		cacheManager.setDefaultCacheName("cacahe_default");
//		cacheManager.setDefaultExpiration(3600);
//		cacheManager.setLoadRemoteCachesOnStartup(true);
//		cacheManager.setSeparator('#');
//		cacheManager.setCachePrefix(new DefaultRedisCachePrefix(":"));
//		cacheManager.setUsePrefix(true);
//		// 设置缓存名称
//		Set<String> cacheNames = new HashSet<String>();
//		cacheNames.add("cacahe_default");
//		cacheNames.add("cacahe_test");
//		// 设置超时时间
//		Map<String, Long> expires = new HashMap<String, Long>();
//		expires.put("cacahe_default", 3600L);
//		expires.put("cacahe_test", 1800L);
//		cacheManager.setExpires(expires);
//		return cacheManager;
//	}
//}
