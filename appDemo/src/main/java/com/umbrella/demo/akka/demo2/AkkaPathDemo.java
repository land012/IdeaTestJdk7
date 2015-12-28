package com.umbrella.demo.akka.demo2;

import akka.actor.*;
import com.umbrella.demo.akka.demo2.actor.GoodNightActor;
import org.apache.log4j.Logger;


/**
 * Created by 大洲 on 15-4-23.
 * 根据 ActorPath 查询 Actor
 * 找不到 Actor
 */
public class AkkaPathDemo {

    private static final Logger log = Logger.getLogger(AkkaPathDemo.class);

    public static final String ACTOR_NAME = "goodnightActor";

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("AS003");
        ActorPath actorPath = actorSystem.child(ACTOR_NAME);
        log.info("1=" + actorPath);       // 1=akka://AS003/user/goodnightActor

        ActorRef actorRef = actorSystem.actorFor(actorPath);
        if(actorRef instanceof EmptyLocalActorRef) {
            log.info("not find");
            actorRef = actorSystem.actorOf(Props.create(GoodNightActor.class), ACTOR_NAME);
        }
        actorRef.tell(new Object(), ActorRef.noSender());
        log.info("2=" + actorRef.path()); // 2=akka://AS003/user/goodnightActor
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ActorRef actorRef2 = actorSystem.actorFor(actorRef.path());
        log.info("3=" + actorRef2.path()); // 3=akka://AS003/user/goodnightActor
        if(actorRef2 instanceof EmptyLocalActorRef) {
            log.info("============================ not find"); // ============================ not find
            actorRef2 = actorSystem.actorOf(Props.create(GoodNightActor.class), ACTOR_NAME);
        } else {
            log.info("============================ find");
        }
        actorRef2.tell(new Object(), ActorRef.noSender());

        actorSystem.shutdown();
    }
}
