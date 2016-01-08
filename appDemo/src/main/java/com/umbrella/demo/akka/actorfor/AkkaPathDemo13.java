package com.umbrella.demo.akka.actorfor;

import akka.actor.*;
import org.apache.log4j.Logger;


/**
 * Created by 大洲 on 15-4-23.
 * 使用绝对路径去查找 Actor
 */
public class AkkaPathDemo13 {

    private static final Logger log = Logger.getLogger(AkkaPathDemo13.class);

    public static final String ACTOR_NAME = "goodnightActor";

    /**
     *
     */
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("AS003");

        ActorRef terminaterActor = actorSystem.actorOf(Props.create(TerminaterActor.class));

        ActorRef actorRef = actorSystem.actorOf(Props.create(GoodNightActor.class), ACTOR_NAME);
        actorRef.tell(new Object(), ActorRef.noSender());
        log.info("1=" + actorRef.path()); // 1=akka://AS003/user/goodnightActor

        ActorRef actorRef2 = actorSystem.actorFor("akka://AS003/user/goodnightActor");
        if(actorRef2 instanceof EmptyLocalActorRef) {
            log.info("============================ not find");
            actorRef2 = actorSystem.actorOf(Props.create(GoodNightActor.class), ACTOR_NAME);
        } else {
            log.info("============================ find");
        }
        actorRef2.tell(Msg.DONE, terminaterActor);

        // 这个命令会立刻结束掉 ActorSystem，不管是不是有没执行完的 Actor
//        actorSystem.shutdown();
    }
}
