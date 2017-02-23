package com.umbrella.demo.apache.commons.pool2redis2;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * Created by xudazhou on 2017/2/23.
 */
public class RedisClientPoolFactory implements PooledObjectFactory<Jedis> {

    private static Logger log = LoggerFactory.getLogger(RedisClientPoolFactory.class);

    @Override
    public PooledObject<Jedis> makeObject() throws Exception {
        log.info("make");
        return new DefaultPooledObject<>( new Jedis("192.168.186.3", 6379));
    }

    @Override
    public void destroyObject(PooledObject<Jedis> p) throws Exception {
        log.info("destroy");
        p.getObject().close();
    }

    @Override
    public boolean validateObject(PooledObject<Jedis> p) {
        log.info("validate");
        return false;
    }

    @Override
    public void activateObject(PooledObject<Jedis> p) throws Exception {
        log.info("activate");
    }

    @Override
    public void passivateObject(PooledObject<Jedis> p) throws Exception {
        log.info("passivate");
    }
}
