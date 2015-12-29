package com.umbrella.demo.akka.pi;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2015/12/29.
 */
public class Master extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(Master.class);

    private final int numOfMessages;
    private final int numOfElement;
    private final int numOfWorkers;

    private double pi;
    private int numOfResult;

    private final long start = System.currentTimeMillis();

    private final ActorRef workerRouter;
    private ActorRef listener;

    public Master(int numOfMessages, int numOfElement, int numOfWorkers) {
        this.numOfMessages = numOfMessages;
        this.numOfElement = numOfElement;
        this.numOfWorkers = numOfWorkers;

        Props props = Props.create(Worker.class).withRouter(new RoundRobinPool(this.numOfWorkers));
        this.workerRouter = this.getContext().actorOf(props, "workerRouter");
    }

    @Override
    public void onReceive(Object o) throws Exception {
        if(o instanceof Calculate) {
            log.info("this is master");
            this.listener = this.getSender();
            for(int i=0; i<numOfMessages; i++) {
                this.workerRouter.tell(new Work(i, numOfElement), this.getSelf());
            }
        } else if(o instanceof Result) {
            Result result = (Result)o;
            pi += result.getValue();
//                log.info("pi=" + pi);
            numOfResult++;
//                log.info("numOfResult;" + numOfResult);
            if(numOfResult==numOfMessages) {
                Duration duration = Duration.apply(System.currentTimeMillis()-start, TimeUnit.MILLISECONDS);
                this.listener.tell(new PiApproximation(pi, duration), this.getSelf());
                this.getContext().stop(this.getSelf());
            }
        } else {
            this.unhandled(o);
        }
    }
}
