package com.umbrella.demo.akka.actorselection;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2015/12/26.
 * 退出 ActorSystem 的方式
 */
public class HelloActor extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(HelloActor.class);

    @Override
    public void onReceive(Object o) throws Exception {
        log.info("I am HelloActor, msg is " + o);
    }
}
