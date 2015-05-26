package com.umbrella.demo.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 大洲 on 15-5-24.
 */
public class RedisClientDemo {

    Jedis jedisClient;

    @Before
    public void before() {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), JedisUtil.REDIS_HOST, JedisUtil.REDIS_PORT);
        jedisClient = pool.getResource();
    }

    /**
     * Demo
     */
    @Test
    public void test1() {
        Jedis jedis = new Jedis(JedisUtil.REDIS_HOST, JedisUtil.REDIS_PORT);
        jedis.set("foo", "bar");
        System.out.println(jedis.get("foo"));
    }

    /**
     * 连接池
     */
    @Test
    public void test2() {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), JedisUtil.REDIS_HOST, JedisUtil.REDIS_PORT);
        Jedis jedis = pool.getResource();
        System.out.println(jedis.ping()); // PONG
        System.out.println(jedis.get("foo"));
    }

    /**
     * 保存 Map
     */
    @Test
    public void test3() {
        Map<String, String> m1 = new HashMap<String, String>();
        m1.put("k1", "m1_v1");
        m1.put("k2", "m1_v2");
        jedisClient.hmset("m1", m1);
        jedisClient.hset("m1", "k3", "m1_v3");
        System.out.println(jedisClient.hget("m1", "k2")); // m1_v2
    }

    /**
     * Redis.info()
     */
    @Test
    public void test3_1() {
        System.out.println(jedisClient.info());
    }

    /**
     * 删除
     */
    @Test
    public void test4() {
        jedisClient.set("foo", "bar");
        System.out.println(jedisClient.get("foo"));
        System.out.println(jedisClient.del("foo")); // 1
    }
}
