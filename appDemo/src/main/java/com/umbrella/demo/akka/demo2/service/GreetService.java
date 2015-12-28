package com.umbrella.demo.akka.demo2.service;

import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import com.umbrella.demo.akka.demo2.myenum.Msg;
import org.apache.log4j.Logger;

/**
 * Created by 大洲 on 15-4-15.
 */
public class GreetService {

    private static final Logger log = Logger.getLogger(GreetService.class);

    private ActorSystem actorSystem;

    public void setActorSystem(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }

    public void fn1() {
        // 获取子结点
        ActorPath greetPath = actorSystem.child("greetactor");
        log.info(greetPath);
        ActorSelection greetActor = actorSystem.actorSelection(greetPath);
        log.info(greetActor.pathString()); // /user/greetactor 相对路径
        greetActor.tell(Msg.GREET, ActorRef.noSender());

    }
}
