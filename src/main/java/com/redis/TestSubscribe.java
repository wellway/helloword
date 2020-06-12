package com.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
/**
 * 订阅测试类
 * @author qiqi
 *
 */
public class TestSubscribe {
	 @Test
	    public void testSubscribe() throws Exception{
		 Jedis jedis = new Jedis("10.26.134.27",6379);
			jedis.auth("123456");
	        RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
	        jedis.subscribe(listener, "redisChatTest");
	        //other code
	    }
}
