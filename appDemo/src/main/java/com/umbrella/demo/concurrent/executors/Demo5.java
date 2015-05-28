package com.umbrella.demo.concurrent.executors;


import com.umbrella.vo.User;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 大洲 on 15-5-27.
 * 在 shutdown 后面提交任务
 * Exception in thread "main" java.util.concurrent.RejectedExecutionException
 */
public class Demo5 {

    private static final Logger log = Logger.getLogger(Demo5.class);

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);

        for(int i=1; i<=10; i++) {
            log.info(String.valueOf(i));
            User u = new User();
            u.setId(i);
            String userName = new String("aaaaaaaaaaaaaa" + i);
            u.setUserName(userName);
            exec.submit(new Task(u));
        }
        exec.shutdown();

        User u1 = new User();
        u1.setId(11);
        exec.submit(new Task(u1));

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
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
