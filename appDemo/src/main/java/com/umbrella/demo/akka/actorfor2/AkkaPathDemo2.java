package com.umbrella.demo.akka.actorfor2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.umbrella.demo.akka.actorfor.Msg;
import org.apache.log4j.Logger;


/**
 * Created by 大洲 on 15-1-16.
 * 使用 getContext() 查找 Actor
 */
public class AkkaPathDemo2 {

    private static Logger log = Logger.getLogger(AkkaPathDemo2.class);

    public static void main(String[] args) {
        /**
         * 先调用 GreetActor，GreetActor 再调用 HelloActor
         */
        log.info("This is Main");
        ActorSystem system = ActorSystem.create("actorsystem");
        final ActorRef greetActor = system.actorOf(Props.create(GreeterActor.class), "greetActor");
        final ActorRef helloActor = system.actorOf(Props.create(HelloActor.class), "helloActor");
        log.info(helloActor.path()); // akka://actorsystem/user/helloActor
        greetActor.tell(Msg.GREET, helloActor);
        log.info("This is Main End");
        system.shutdown();
    }
}
