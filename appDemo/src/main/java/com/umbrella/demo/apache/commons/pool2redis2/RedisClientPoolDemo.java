package com.umbrella.demo.apache.commons.pool2redis2;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2017/2/23.
 * 实现接口 PooledObjectFactory
 */
public class RedisClientPoolDemo {

    /**
     * 连接池
     * borrow 当连接池中没有对象时，make activate；当连接池中有对象时，activate
     * return passivate
     * clear destory
     */
    @Test
    public void test1() {
        GenericObjectPoolConfig cfg = new GenericObjectPoolConfig();
        cfg.setMaxWaitMillis(2000);
        cfg.setMinEvictableIdleTimeMillis(1000);
        GenericObjectPool<Jedis> pool = new GenericObjectPool<>(new RedisClientPoolFactory("192.168.186.3", 6379), cfg);

        System.out.println("----1----" + pool.getNumActive()); // 0
        System.out.println("----1----" + pool.getNumIdle()); // 0
        System.out.println("----1----" + pool.getNumWaiters()); // 0

        Jedis jedis = null;
        Jedis jedis2 = null;

        try {
            jedis = pool.borrowObject(); // make activate
            jedis2 = pool.borrowObject();
            System.out.println("----2----" + pool.getNumActive()); // 2
            System.out.println("----2----" + pool.getNumIdle()); // 0
            System.out.println("----2----" + pool.getNumWaiters()); // 0
            System.out.println(jedis.get("foo"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                pool.returnObject(jedis); // passivate
            }
            if (jedis2 != null) {
                pool.returnObject(jedis2); // passivate
            }
        }

        System.out.println("----3----" + pool.getNumActive()); // 0
        System.out.println("----3----" + pool.getNumIdle()); // 2
        System.out.println("----3----" + pool.getNumWaiters()); // 0

        pool.clear(); // destory

        System.out.println("----4----" + pool.getNumActive()); // 0
        System.out.println("----4----" + pool.getNumIdle()); // 0
        System.out.println("----4----" + pool.getNumWaiters()); // 0

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            jedis = pool.borrowObject(); // make activate
            System.out.println(jedis.rpush("l1", "d"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                pool.returnObject(jedis); // passivate
            }
        }

        System.out.println("----5----" + pool.getNumActive()); // 0
        System.out.println("----5----" + pool.getNumIdle()); // 1
        System.out.println("----5----" + pool.getNumWaiters()); // 0

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            jedis = pool.borrowObject(); // activate
            System.out.println(jedis.rpush("l1", "d"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                pool.returnObject(jedis); // passivate
            }
        }

        System.out.println("----6----" + pool.getNumActive()); // 0
        System.out.println("----6----" + pool.getNumIdle()); // 1
        System.out.println("----6----" + pool.getNumWaiters()); // 0
    }

    /**
     * client 被 kill
     */
    @Test
    public void test2() {
        GenericObjectPoolConfig cfg = new GenericObjectPoolConfig();
        cfg.setMinEvictableIdleTimeMillis(1000);
        GenericObjectPool<Jedis> pool = new GenericObjectPool<>(new RedisClientPoolFactory("192.168.186.3", 6379), cfg);

        System.out.println("----1----" + pool.getNumActive()); // 0
        System.out.println("----1----" + pool.getNumIdle()); // 0
        System.out.println("----1----" + pool.getNumWaiters()); // 0

        Jedis jedis = null;

        try {
            jedis = pool.borrowObject(); // make activate
            System.out.println("----2----" + pool.getNumActive()); // 2
            System.out.println("----2----" + pool.getNumIdle()); // 0
            System.out.println("----2----" + pool.getNumWaiters()); // 0
            System.out.println(jedis.get("foo"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                pool.returnObject(jedis); // passivate
            }
        }

        System.out.println("----3----" + pool.getNumActive()); // 0
        System.out.println("----3----" + pool.getNumIdle()); // 2
        System.out.println("----3----" + pool.getNumWaiters()); // 0

        try {
            TimeUnit.SECONDS.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            jedis = pool.borrowObject(); // activate
            System.out.println(jedis.get("foo"));
        } catch (Exception e) {
            // 当连接被kill后，会抛异常
            // 池并没有检查对象的可用性
            System.out.println(e);
        } finally {
            if (jedis != null) {
                pool.returnObject(jedis); // passivate
            }
        }

        System.out.println("----4----" + pool.getNumActive()); // 1
        System.out.println("----4----" + pool.getNumIdle()); // 1
        System.out.println("----4----" + pool.getNumWaiters()); // 0
    }
}
