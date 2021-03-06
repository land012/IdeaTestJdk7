package com.umbrella.concurrent.thread;

import com.umbrella.concurrent.executors.Demo1TasksOverJvm;
import org.apache.log4j.Logger;

/**
 * 最基本的线程写法
 */
public class ThreadDemo {
    private static final Logger log = Logger.getLogger(Demo1TasksOverJvm.class);

    public static void main(String[] args) {
        new Thread(new Task(0)).start();
        new Thread(new Task(1)).start();
        new Thread(new Task(2)).start();
        log.info("i am main");
    }

    static class Task implements Runnable {

        private long id;

        Task(long id) {
            this.id = id;
        }

        @Override
        public void run() {
            log.info("begin " + id);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("end " + id);
        }
    }
}
