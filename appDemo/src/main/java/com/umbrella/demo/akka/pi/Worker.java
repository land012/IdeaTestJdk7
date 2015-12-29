package com.umbrella.demo.akka.pi;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2015/12/29.
 */
public class Worker extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(Worker.class);

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
