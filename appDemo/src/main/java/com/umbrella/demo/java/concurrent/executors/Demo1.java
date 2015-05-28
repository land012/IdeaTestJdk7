package com.umbrella.demo.java.concurrent.executors;


import com.umbrella.vo.User;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 大洲 on 15-5-27.
 * 当任务远多于线程个数时，任务是不是都会被添加
 */
public class Demo1 {

    private static final Logger log = Logger.getLogger(Demo1.class);

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        for(int i=1; i<=1000000; i++) {
            log.info(String.valueOf(i));
            User u = new User();
            u.setId(i);
            String userName = "a";
            for(int j=0; j<1000; j++) {
                userName += userName;
            }
            userName += i;
            u.setUserName(userName);
            exec.execute(new Task(u));
        }
        log.info("i am main");
        exec.shutdown();
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
