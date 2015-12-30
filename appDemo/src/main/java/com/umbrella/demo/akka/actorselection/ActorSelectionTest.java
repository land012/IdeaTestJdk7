package com.umbrella.demo.akka.actorselection;

import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by xudazhou on 2015/12/26.
 */
public class ActorSelectionTest {

    private ActorSystem system;

    @Before
    public void before() {
        // 指定akka配置文件
        System.setProperty("config.resource", "akka.conf");
        system = ActorSystem.create("AS001");
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
