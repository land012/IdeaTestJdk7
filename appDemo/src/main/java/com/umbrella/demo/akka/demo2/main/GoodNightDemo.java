package com.umbrella.demo.akka.demo2.main;

import akka.actor.*;
import com.umbrella.demo.akka.demo2.actor.GoodNightActor;
import org.apache.log4j.Logger;


/**
 * Created by 大洲 on 15-4-23.
 */
public class GoodNightDemo {

    private static final Logger log = Logger.getLogger(GoodNightActor.class);

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("AS003");
        ActorPath actorPath = actorSystem.child("goodnight");
        ActorRef actorRef = actorSystem.actorFor(actorPath);
        if(actorRef instanceof EmptyLocalActorRef) {
            log.info("not find");
            actorRef = actorSystem.actorOf(Props.create(GoodNightActor.class), "goodnight");
        }
        actorRef.tell("1", ActorRef.noSender());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        actorRef = actorSystem.actorFor(actorPath);
        if(actorRef instanceof EmptyLocalActorRef) {
            log.info("not find");
            actorRef = actorSystem.actorOf(Props.create(GoodNightActor.class), "goodnight");
        } else {
            log.info("find");
        }
        actorRef.tell("1", ActorRef.noSender());
    }
}
