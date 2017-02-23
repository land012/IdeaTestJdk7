package com.umbrella.demo.apache.commons.pool2redis;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by xudazhou on 2017/2/23.
 * 继承 BasePooledObjectFactory
 */
public class RedisClientPoolDemo {
    @Test
    public void test1() {
        GenericObjectPoolConfig cfg = new GenericObjectPoolConfig();
        GenericObjectPool<Jedis> pool = new GenericObjectPool<>(new RedisClientPoolFactory(), cfg);

        Jedis jedis = null;

        try {
            jedis = pool.borrowObject();
            System.out.println(jedis.get("foo"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                pool.returnObject(jedis);
            }
        }

        try {
            jedis = pool.borrowObject();
            System.out.println(jedis.rpush("l1", "d"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                pool.returnObject(jedis);
            }
        }
    }
}
