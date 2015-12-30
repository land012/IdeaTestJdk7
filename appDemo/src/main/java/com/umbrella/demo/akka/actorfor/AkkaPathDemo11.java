package com.umbrella.demo.akka.actorfor;

import akka.actor.*;
import org.apache.log4j.Logger;


/**
 * Created by 大洲 on 15-4-23.
 * 根据 ActorPath 查询 Actor
 * 可以找到 Actor，之前测试之所以找不到，是因为 Actor里调用了 stop()方法，把自己kill掉了
 */
public class AkkaPathDemo11 {

    private static final Logger log = Logger.getLogger(AkkaPathDemo11.class);

    public static final String ACTOR_NAME = "goodnightActor";

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("AS003");
        ActorRef terminaterActor = actorSystem.actorOf(Props.create(TerminaterActor.class));
        // 不管是否存在这个 children，都会创建 ActorPath
        ActorPath actorPath = actorSystem.child(ACTOR_NAME);
        log.info("1=" + actorPath);       // 1=akka://AS003/user/goodnightActor

        // 第一次肯定找不到，所以需要创建
        ActorRef actorRef = actorSystem.actorFor(actorPath);
        if(actorRef instanceof EmptyLocalActorRef) {
            log.info("============================ not find"); // ============================ not find
            actorRef = actorSystem.actorOf(Props.create(GoodNightActor.class), ACTOR_NAME);
        }
        actorRef.tell(new Object(), ActorRef.noSender());
        log.info("2=" + actorRef.path()); // 2=akka://AS003/user/goodnightActor

        // 这一次可以找到
        ActorRef actorRef2 = actorSystem.actorFor(actorPath);
        if(actorRef2 instanceof EmptyLocalActorRef) {
            log.info("============================ not find");
            actorRef2 = actorSystem.actorOf(Props.create(GoodNightActor.class), ACTOR_NAME);
        } else {
            log.info("============================ find"); // ============================ find
        }
        actorRef2.tell(Msg.DONE, terminaterActor);
    }

}
