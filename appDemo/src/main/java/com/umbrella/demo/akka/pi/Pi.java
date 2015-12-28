package com.umbrella.demo.akka.pi;

import akka.actor.*;
import akka.japi.Creator;
import akka.routing.RoundRobinPool;
import org.apache.log4j.Logger;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by 大洲 on 15-1-22.
 */
public class Pi {

    private static Logger log = Logger.getLogger(Pi.class);

    // 消费者个数
    private static final int NUM_OF_WORKER = 3;
    // 任务数
    private static final int NUM_OF_MESSAGES = 5;
    // 每个任务中元素个数
    private static final int NUM_OF_ELEMENTS = 20;

    static class Calculate {

    }

    static class Work {
        private final int start;
        private final int numOfElements;

        Work(int start, int numOfElements) {
            this.start = start;
            this.numOfElements = numOfElements;
        }

        public int getStart() {
            return start;
        }

        public int getNumOfElements() {
            return numOfElements;
        }
    }

    static class Result {
        private final double value;

        Result(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }
    }

    static class PiApproximation {
        private final double pi;
        private final Duration duration;

        PiApproximation(double pi, Duration duration) {
            this.pi = pi;
            this.duration = duration;
        }

        public double getPi() {
            return pi;
        }

        public Duration getDuration() {
            return duration;
        }
    }

    /**
     * 计算 chunk
     */
    public static class Worker extends UntypedActor {

        @Override
        public void onReceive(Object o) throws Exception {
            log.info("this is worker");
            if(o instanceof Work) {
                Work work = (Work)o;
//                log.info("begin cal:" + work.getStart());
                double result = calculateForPi(work.getStart(), work.getNumOfElements());
                this.getSender().tell(new Result(result), this.getSelf());
            } else {
                unhandled(o);
            }
        }

        /**
         * 类似分页
         * @param start 当前页起始记录
         * @param numOfElement 每页记录大小
         * @return 当前 chunk 的结果
         */
        private double calculateForPi(int start, int numOfElement) {
            double acc = 0;
            for(int i=start*numOfElement; i<=start*numOfElement+numOfElement-1; i++) {
                acc += 4.0 * (1 - i%2*2) / (i*2 + 1);
            }
            return acc;
        }
    }

    /**
     * 主Actor
     */
    public static class Master extends UntypedActor {
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

            this.workerRouter = this.getContext().actorOf(Props.create(Worker.class).withRouter(new RoundRobinPool(this.numOfWorkers)), "workerRouter");
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

    /**
     * 打印最终结果
     */
    public static class Listener extends UntypedActor {

        @Override
        public void onReceive(Object o) throws Exception {
            log.info("this is listener");
            if(o instanceof PiApproximation) {
                PiApproximation approximation = (PiApproximation)o;
                System.out.println(String.format("pi:%s,cost:%s\n", approximation.getPi(), approximation.getDuration()));
                this.getContext().system().shutdown();
            } else {
                this.unhandled(o);
            }
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("PiSystem");
        final ActorRef listener = system.actorOf(Props.create(Listener.class), "listener");
        ActorRef master = system.actorOf(Props.create(new Creator<Actor>() {
            @Override
            public Actor create() throws Exception {
                return new Master(NUM_OF_MESSAGES, NUM_OF_ELEMENTS, NUM_OF_WORKER);
            }
        }), "master");
        master.tell(new Calculate(), listener);
        log.info("this is main");
    }
}
