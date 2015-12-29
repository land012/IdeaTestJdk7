package com.umbrella.demo.akka.pi;

import akka.actor.*;
import akka.japi.Creator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 计算 Pi —— Akka2.0官方文档
 * Created by 大洲 on 15-1-22.
 */
public class Pi {

    private static Logger log = LoggerFactory.getLogger(Pi.class);

    // 消费者个数
    private static final int NUM_OF_WORKER = 3;
    // 任务数
    private static final int NUM_OF_MESSAGES = 50;
    // 每个任务中元素个数
    private static final int NUM_OF_ELEMENTS = 20;

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("PiSystem");
        final ActorRef listener = system.actorOf(Props.create(Listener.class), "listener");
        ActorRef master = system.actorOf(Props.create(new Creator<Actor>() {
                    @Override
                    public Actor create() throws Exception {
                        return new Master(NUM_OF_MESSAGES, NUM_OF_ELEMENTS, NUM_OF_WORKER);
                    }
                }), "master");
        master.tell(new Calculate(), listener);
        log.info("this is main");
    }
}
