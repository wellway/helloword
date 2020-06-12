package com.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * 发布消息测试类
 * 
 * @author qiqi
 *
 */
public class TestPublish {
	@Test
	public void testPublish() throws Exception {
		Jedis jedis = new Jedis("10.26.134.27",6379);
		jedis.auth("123456");
		long s=jedis.publish("redisChatTest", "我是天才");
		System.out.println("num:"+s);
		Thread.sleep(5000);
		s=jedis.publish("redisChatTest", "我牛逼");
		Thread.sleep(5000);
		s=jedis.publish("redisChatTest", "哈哈");
	}

}
