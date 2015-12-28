package com.umbrella.demo.akka.spring0;

import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import org.apache.log4j.Logger;

/**
 * Created by 大洲 on 15-4-15.
 */
public class GoodmorningService {

    private static final Logger log = Logger.getLogger(GoodmorningService.class);

    private ActorSystem actorSystem;

    public void setActorSystem(ActorSystem actorSystem) {
        this.actorSystem = actorSystem;
    }

    public void fn1() {
        // 根据绝对路径获取ActorRef
        ActorSelection goodmorningSelection = actorSystem.actorSelection("akka://actorsystem/user/goodmorning");
        log.info(goodmorningSelection.pathString());
        goodmorningSelection.tell("", null);
    }
}
