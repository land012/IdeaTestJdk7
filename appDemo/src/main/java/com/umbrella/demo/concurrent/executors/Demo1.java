package com.umbrella.demo.concurrent.executors;


import com.umbrella.vo.User;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 大洲 on 15-5-27.
 * 当任务远多于线程个数时，任务是不是都会被添加而导致内存溢出
 * 是的
 */
public class Demo1 {

    private static final Logger log = Logger.getLogger(Demo1.class);

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);

        /**
         * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
         * 主线程挂掉了，子线程照样跑！！！！！
         */
        for(int i=1; i<=1000000; i++) {
            log.info(String.valueOf(i));
            User u = new User();
            u.setId(i);
            String userName = new String("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + i);
            u.setUserName(userName);
            exec.execute(new Task(u));
        }
        exec.shutdown();

        log.info("i am main end");
    }

    static class Task implements Runnable {

        private User user;

        Task(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            log.info("i am " + user.getId());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
