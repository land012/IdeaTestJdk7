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

    private String host;
    private int port;

    public RedisClientPoolFactory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public PooledObject<Jedis> makeObject() throws Exception {
        log.info("make");
        return new DefaultPooledObject<>( new Jedis(this.host, this.port));
    }

    @Override
    public void destroyObject(PooledObject<Jedis> p) throws Exception {
        log.info("destroy");
        p.getObject().close();
    }

    @Override
    public boolean validateObject(PooledObject<Jedis> p) {
        log.info("validate");
        return p.getObject().isConnected();
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
