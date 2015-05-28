package com.umbrella.bigdata.zookeeper;

import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Created by 大洲 on 15-5-27.
 */
public class MyWatcher implements Watcher {

    private static final Logger log = Logger.getLogger(MyWatcher.class);

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info(watchedEvent.getType());
    }
}
