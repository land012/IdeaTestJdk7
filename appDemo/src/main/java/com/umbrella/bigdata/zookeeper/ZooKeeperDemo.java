package com.umbrella.bigdata.zookeeper;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Created by 大洲 on 15-5-27.
 */
public class ZooKeeperDemo {

    private static final Logger log = Logger.getLogger(ZooKeeperDemo.class);

    private static final String PATH = "/zk_test";

    public static void main(String[] args) {
        try {
            ZooKeeper zk = new ZooKeeper("192.168.120.128:2181", 3000, new MyWatcher());
            Stat stat = zk.exists(PATH, false);
            log.info(stat.getNumChildren());

            String res = zk.create(PATH + "/", "haha".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            System.out.println(res);

//            System.out.println(zk.exists("/zk_test1", false)); // null

            Thread.sleep(60 * 1000);

        } catch (IOException e) {
            log.info("", e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }
}
