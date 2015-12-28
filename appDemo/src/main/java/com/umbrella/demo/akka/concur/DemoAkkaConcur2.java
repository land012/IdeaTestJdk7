package com.umbrella.demo.akka.concur;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;


/**
 * Created by 大洲 on 15-1-16.
 * 多线程访问 两个Actor
 * 会有并发问题
 *
 */
public class DemoAkkaConcur2 {

    private static final Logger log = LoggerFactory.getLogger(DemoAkkaConcur2.class);

    public static void main(String[] args) {

        log.info("=========================== This is Main ===========================");

        ActorSystem system = ActorSystem.create("AS001");

        ActorRef helloActor = system.actorOf(Props.create(HelloActor.class), "helloActor");
        ActorRef greetActor = system.actorOf(Props.create(GreetActor.class), "greetActor");

        MyCounter myCounter = new MyCounter(0);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for(int i=0; i<1000; i++) {
            if(i%2==0) {
                new Thread(new MyAkkaThread(helloActor, myCounter, countDownLatch)).start();
            } else {
                new Thread(new MyAkkaThread(greetActor, myCounter, countDownLatch)).start();
            }
        }

        countDownLatch.countDown();

        // myCounter is 0 因为是异步线程
        log.info("myCounter is " + myCounter.get());

        log.info("=========================== This is Main End ===========================");
//        system.shutdown();
    }

}
