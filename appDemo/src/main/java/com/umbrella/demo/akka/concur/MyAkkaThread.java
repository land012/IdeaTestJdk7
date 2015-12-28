package com.umbrella.demo.akka.concur;

import akka.actor.ActorRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created by xudazhou on 2015/12/26.
 */
public class MyAkkaThread implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(MyAkkaThread.class);

    private ActorRef actorRef;
    private Object object;
    private CountDownLatch countDownLatch;

    public MyAkkaThread(ActorRef actorRef, Object object, CountDownLatch countDownLatch) {
        this.actorRef = actorRef;
        this.object = object;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            this.countDownLatch.await();
            this.actorRef.tell(object, ActorRef.noSender());
        } catch (Exception e) {
            log.info("", e);
        }
    }
}
