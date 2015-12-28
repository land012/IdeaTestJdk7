package com.umbrella.demo.akka.demo1;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2015/12/26.
 */
public class LeahActor extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(LeahActor.class);

    @Override
    public void preStart() throws Exception {
        log.info("I am LeahActor preStart");
        ActorRef ref = getContext().actorOf(Props.create(DizonActor.class), "dizonActor");
        ref.tell(new Object(), getSelf());
    }

    @Override
    public void onReceive(Object o) throws Exception {
        log.info("I am LeahActor onReceive");
        this.getContext().stop(getSelf()); // 逐个kill掉自己的子 actor，之后自杀
    }
}
