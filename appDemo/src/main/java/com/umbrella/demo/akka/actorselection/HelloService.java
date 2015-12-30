package com.umbrella.demo.akka.actorselection;

import akka.actor.*;
import org.apache.log4j.Logger;

/**
 * Created by 大洲 on 15-4-17.
 */
public class HelloService {

    private static final Logger log = Logger.getLogger(HelloService.class);

    ActorSystem actorSystem;

    public HelloService(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }

    public void fn1() {
        // 只会发送到 helloactor
//        ActorPath actorPath = actorSystem.child("helloactor");
//        ActorSelection helloSelection = actorSystem.actorSelection(actorPath);

        // 只会发送到 helloactor
//        ActorSelection helloSelection = actorSystem.actorSelection("user/*");

        // 会发送到 helloactor的所有子结点
        ActorSelection helloSelection = actorSystem.actorSelection("user/helloactor/*");

        helloSelection.tell(Msg.HELLO, ActorRef.noSender());
    }
}
