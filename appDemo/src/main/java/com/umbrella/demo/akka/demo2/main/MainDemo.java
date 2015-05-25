package com.umbrella.demo.akka.demo2.main;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.umbrella.demo.akka.demo2.actor.HelloActor;
import com.umbrella.demo.akka.demo2.service.HelloService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by 大洲 on 15-1-16.
 */
public class MainDemo {

    private static Logger log = Logger.getLogger(MainDemo.class);

    private ActorSystem system;

    @Before
    public void before() {
        // 指定akka配置文件
        System.setProperty("config.resource", "akka.conf");
        system = ActorSystem.create("AS001");
    }

    public static void main(String[] args) {
        /**
         * 先调用 GreetActor，GreetActor 再调用 HelloActor
         */
        log.info("=========================== This is Main ===========================");
        // 指定akka配置文件
        System.setProperty("config.resource", "akka.conf");

        ActorSystem system = ActorSystem.create("AS001");

        /**
         * Goodmorning Actor
         */
//        ActorRef goodmorningActor = system.actorOf(Props.create(GoodmorningActor.class), "goodmorning");
////        goodmorningActor.tell("", ActorRef.noSender());
//        log.info(goodmorningActor.path());
//
//        GoodmorningService goodmorningService = new GoodmorningService();
//        goodmorningService.setActorSystem(system);
//        goodmorningService.fn1();

        log.info("=========================== This is Main End ===========================");
        system.shutdown();
    }

    /**
     * ActorSelection
     */
    @Test
    public void test1() {
        /**
         * HelloActor
         */
        HelloService helloService = new HelloService(system);
        system.actorOf(Props.create(HelloActor.class), "helloactor");
        helloService.fn1();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
