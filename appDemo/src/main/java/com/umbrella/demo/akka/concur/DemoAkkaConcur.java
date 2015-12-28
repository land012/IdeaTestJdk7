package com.umbrella.demo.akka.concur;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;


/**
 * Created by 大洲 on 15-1-16.
 * 多线程访问 Actor
 * Actor 单线程执行任务，没有并发问题
 */
public class DemoAkkaConcur {

    private static final Logger log = Logger.getLogger(DemoAkkaConcur.class);

    public static void main(String[] args) {
        log.info("=========================== This is Main ===========================");
        ActorSystem system = ActorSystem.create("AS001");

        ActorRef helloActor = system.actorOf(Props.create(HelloActor.class), "helloActor");

        MyCounter myCounter = new MyCounter(0);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // 任务会被顺序执行
        for(int i=0; i<1000; i++) {
            new Thread(new MyAkkaThread(helloActor, myCounter, countDownLatch)).start();
        }

        countDownLatch.countDown();

        // myCounter is 0 因为是异步线程
        log.info("myCounter is " + myCounter.get());

        log.info("=========================== This is Main End ===========================");
//        system.shutdown();
    }

}
