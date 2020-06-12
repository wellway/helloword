package com.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.commons.LogManagement;

/**
 * yalongz
 */
public class JedisUtil {
	private static String		JEDIS_IP;
	private static int			JEDIS_PORT;
	private static String		JEDIS_PASSWORD;
	private static JedisPool	jedisPool;
	static {
		//		JEDIS_IP = PushConfig.getCurrent().jedisIp;
		//		JEDIS_PORT = PushConfig.getCurrent().jedisPort;
		//		JEDIS_PASSWORD = PushConfig.getCurrent().jedisPwd;

		JEDIS_IP = "127.0.0.1";
		JEDIS_PORT = 6379;
		JEDIS_PASSWORD = "123456";
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(10);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(5000L);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		config.setTestWhileIdle(true);
		config.setBlockWhenExhausted(true);
		config.setMinEvictableIdleTimeMillis(60000L);
		config.setTimeBetweenEvictionRunsMillis(3000L);
		config.setNumTestsPerEvictionRun(-1);
		jedisPool = new JedisPool(config, JEDIS_IP, JEDIS_PORT, 60000);
		//		jedisPool = new JedisPool(config, JEDIS_IP, JEDIS_PORT, 60000, JEDIS_PASSWORD);
	}

	/**
	 * 获取数据
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			LogManagement.Info("" + jedis.exists(key));
			value = jedis.get(key);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			close(jedis);
		}
		return value;
	}

	/**
	 * 释放redis
	 * 
	 * @param jedis
	 */
	private static void close(Jedis jedis) {
		try {
			if (jedis != null)
				jedis.close();
		} catch (Exception e) {
			if (jedis.isConnected()) {
				jedis.quit();
				jedis.disconnect();
			}
		}
	}

	public static byte[] get(byte[] key) {
		byte[] value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			close(jedis);
		}

		return value;
	}

	public static void set(byte[] key, byte[] value) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
	}

	public static void set(byte[] key, byte[] value, int time) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
			jedis.expire(key, time);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
	}

	public static void hset(byte[] key, byte[] field, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hset(key, field, value);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			close(jedis);
		}
	}

	public static void hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hset(key, field, value);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
	}

	public static Boolean set(String key, String value) {
		Jedis jedis = null;
		Boolean isTrue = false;
		try {
			jedis = jedisPool.getResource();
			jedis.select(6);
			jedis.set(key, value);
		} catch (Exception e) {
			isTrue = true;
			LogManagement.Error("", e, true);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return isTrue;
	}

	/**
	 * 获取数据
	 *
	 * @param key
	 * @return
	 */
	public static String hget(String key, String field) {

		String value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.hget(key, field);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return value;
	}

	/**
	 * 获取数据
	 *
	 * @param key
	 * @return
	 */
	public static byte[] hget(byte[] key, byte[] field) {

		byte[] value = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			value = jedis.hget(key, field);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return value;
	}

	public static void hdel(byte[] key, byte[] field) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hdel(key, field);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
	}

	/**
	 * 存储REDIS队列 顺序存储
	 * 
	 * @param key
	 *            reids键名
	 * @param value
	 *            键值
	 */
	public static void lpush(byte[] key, byte[] value) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.lpush(key, value);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			close(jedis);
		}
	}

	/**
	 * 存储REDIS队列 反向存储
	 * 
	 * @param key
	 *            reids键名
	 * @param value
	 *            键值
	 */
	public static void rpush(byte[] key, byte[] value) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.rpush(key, value);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
	}

	/**
	 * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端
	 * 
	 * @param key
	 *            reids键名
	 * @param destination
	 *            键值
	 */
	public static void rpoplpush(byte[] key, byte[] destination) {

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.rpoplpush(key, destination);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);

		}
	}

	/**
	 * 获取队列数据
	 * 
	 * @param key
	 *            键名
	 * @return
	 */
	public static List lpopList(byte[] key) {
		List list = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			list = jedis.lrange(key, 0, -1);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return list;
	}

	/**
	 * 获取队列数据
	 * 
	 * @param key
	 *            键名
	 * @return
	 */
	public static byte[] rpop(byte[] key) {
		byte[] bytes = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			bytes = jedis.rpop(key);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return bytes;
	}

	public static void hmset(Object key, Map hash) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hmset(key.toString(), hash);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
	}

	public static void hmset(Object key, Map hash, int time) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.hmset(key.toString(), hash);
			jedis.expire(key.toString(), time);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
	}

	public static void mset() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.mset("key01", "value01", "key02", "value02", "key03", "value03");
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
	}

	public static void sadd() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.sadd("hi", "good", "good1", "good3", "good10");
			System.out.println(jedis.smembers("hi"));
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
	}

	public static List hmget(Object key, String... fields) {
		List result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.hmget(key.toString(), fields);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return result;
	}

	public static Set hkeys(String key) {
		Set result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.hkeys(key);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return result;
	}

	public static List<String> hvals(String key) {
		List<String> result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.hvals(key);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return result;
	}

	public static List lrange(byte[] key, int from, int to) {
		List result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.lrange(key, from, to);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return result;
	}

	public static Map hgetAll(byte[] key) {
		Map result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.hgetAll(key);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return result;
	}

	public static void del(byte[] key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
	}

	public static long llen(byte[] key) {
		long len = 0;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.llen(key);
		} catch (Exception e) {
			LogManagement.Error("", e, false);
		} finally {
			//返还到连接池
			close(jedis);
		}
		return len;
	}
}