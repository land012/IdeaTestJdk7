package com.umbrella.bigdata.redis;

import com.umbrella.util.serialize.SerializeUtil;
import com.umbrella.vo.User;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
//        jedis.set("foo", "bar");
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
        jedisClient.expire("m1", 5); // 5秒后失效
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

    /**
     * 队列
     */
    @Test
    public void test5() {
        /*
         * l 是从左边 push
         * r 是从右边 push
         * lrange key start end : 会包含 end元素(end大于队列长度也没有关系)
         */
//        jedisClient.lpush("l1", "b");
//        jedisClient.rpush("l1", "c");
//        jedisClient.lpush("l1", "a");
        Long l1len = jedisClient.llen("l1");
        System.out.println(l1len);
        List<String> l1 = jedisClient.lrange("l1", 0, l1len + 3);
        System.out.println(l1);
        List<String> l2 = jedisClient.lrange("l1", 0, l1len - 1);
        System.out.println(l2);
//        jedisClient.lrem
    }

    /**
     * 队列
     * 当队列是的值被 pop光时，队列会被删除，这时再 pop，返回 null
     */
    @Test
    public void test6() {
        String str1 = jedisClient.lpop("l1");
        System.out.println("str1=" + str1);

        String str2 = jedisClient.lpop("l1");
        if(str2==null) {
            System.out.println("str2 is null");
        } else {
            System.out.println("str2 is not null, str2=" + str2);
        }
    }

    /**
     * 队列
     */
    @Test
    public void test7() {
        jedisClient.rpush("l2", "a");
        jedisClient.rpush("l2", "b");
        jedisClient.rpush("l2", "c");
        List<String> l2 = jedisClient.lrange("l2", 0, jedisClient.llen("l2"));
        System.out.println(l2);
    }

    /**
     * 保存对象
     * 需要手动对对象做序列化
     */
    @Test
    public void test8() throws Exception {
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("Yukimura");
        u1.setBirthDay(new Date());
        jedisClient.set("u1".getBytes("utf-8"), SerializeUtil.serialize(u1));
        User u2 = SerializeUtil.unserialize(jedisClient.get("u1".getBytes("utf-8")), User.class);
        System.out.println(u2);
    }


}
