package com.umbrella.bigdata.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by xudazhou on 2017/2/6.
 */
public class PubSubListener extends JedisPubSub {

    private static final Logger log = LoggerFactory.getLogger(PubSubListener.class);

    @Override
    public void onMessage(String channel, String message) {
        log.info("channel={}, message={}", channel, message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        log.info("channel={}, count={}", channel, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        super.onUnsubscribe(channel, subscribedChannels);
    }
}
