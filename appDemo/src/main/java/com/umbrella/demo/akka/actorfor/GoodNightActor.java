package com.umbrella.demo.akka.actorfor;

import akka.actor.UntypedActor;
import org.apache.log4j.Logger;

/**
 * Created by 大洲 on 15-4-23.
 */
public class GoodNightActor extends UntypedActor {

    private static final Logger log = Logger.getLogger(GoodNightActor.class);

    @Override
    public void onReceive(Object o) throws Exception {
        log.info("Good Nigth");
        if(o==Msg.DONE) {
//            log.info("I will DONE");
//            this.getContext().stop(this.getSelf()); // 这种做法只能 kill 掉自己，不会结束 ActorSystem
            this.getSender().tell(new Object(), this.getSelf());
        }
    }
}
