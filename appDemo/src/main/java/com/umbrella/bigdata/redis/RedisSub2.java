package com.umbrella.bigdata.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by xudazhou on 2017/2/6.
 */
public class RedisSub2 {

    public static final String REDIS_HOST = "192.168.186.3";
    public static final int REDIS_PORT = 6379;

    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), REDIS_HOST, REDIS_PORT);
        Jedis jedisClient = pool.getResource();
        jedisClient.subscribe(new PubSubListener(), "mq1");
    }
}
