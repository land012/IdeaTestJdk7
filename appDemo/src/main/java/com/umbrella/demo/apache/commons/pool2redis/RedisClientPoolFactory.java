package com.umbrella.demo.apache.commons.pool2redis;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * Created by xudazhou on 2017/2/23.
 */
public class RedisClientPoolFactory extends BasePooledObjectFactory<Jedis> {

    private static Logger log = LoggerFactory.getLogger(RedisClientPoolFactory.class);

    @Override
    public Jedis create() throws Exception {
        log.info("create");
        return new Jedis("192.168.186.3", 6379);
    }

    @Override
    public PooledObject<Jedis> wrap(Jedis obj) {
        log.info("wrap");
        return new DefaultPooledObject<>(obj);
    }
}
