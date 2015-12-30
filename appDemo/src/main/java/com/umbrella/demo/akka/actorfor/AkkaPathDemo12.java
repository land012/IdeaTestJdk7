package com.umbrella.demo.akka.actorfor;

import akka.actor.*;
import org.apache.log4j.Logger;


/**
 * Created by 大洲 on 15-4-23.
 * 使用相对路径查找 Actor
 */
public class AkkaPathDemo12 {

    private static final Logger log = Logger.getLogger(AkkaPathDemo12.class);

    public static final String ACTOR_NAME = "goodnightActor";

    /**
     * 根据 ActorPath 仍然找不到 ActorRef
     */
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("AS003");
        ActorRef terminaterActor = actorSystem.actorOf(Props.create(TerminaterActor.class));

        ActorRef actorRef = actorSystem.actorOf(Props.create(GoodNightActor.class), ACTOR_NAME);
        actorRef.tell(new Object(), ActorRef.noSender());
        log.info("1=" + actorRef.path()); // 1=akka://AS003/user/goodnightActor

        ActorRef actorRef2 = actorSystem.actorFor("user/goodnightActor");
        if(actorRef2 instanceof EmptyLocalActorRef) {
            log.info("============================ not find"); // ============================ not find
            actorRef2 = actorSystem.actorOf(Props.create(GoodNightActor.class), ACTOR_NAME);
        } else {
            log.info("============================ find");
        }
        actorRef2.tell(Msg.DONE,terminaterActor);
    }

}
