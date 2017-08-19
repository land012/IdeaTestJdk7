package com.umbrella.concurrent.submit;

import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 大洲 on 15-2-12.
 * execute + Runnable
 * 子线程会抛出异常，但是主线程catch不到
 * 异常会导致原来的子线程死掉，但线程池会再创建一个子线程，继续执行下面的任务
 */
public class ExecutorsDemo11 {

    private static final Logger log = Logger.getLogger(ExecutorsDemo11.class);

    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newFixedThreadPool(1);
            for(int i=0; i<6; i++) {
                exec.execute(new Work(i));
            }
            exec.shutdown();
        } catch (Exception e) {
            log.info("=============================", e);
        }
        log.info("this is main");
    }

    static class Work implements Runnable {
        private int id;

        private Work(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            if(id==3) throw new RuntimeException("here is an exception, id=" + id);
            log.info("i am " + id);
        }
    }
}
