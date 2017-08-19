package com.umbrella.concurrent.executors;


import com.umbrella.vo.User;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 大洲 on 15-5-27.
 * 执行了 shutdown，会不会执行还没有执行的任务？
 * 线程池会执行完所有队列中的任务才会退出
 */
public class Demo4 {

    private static final Logger log = Logger.getLogger(Demo4.class);

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);

        for(int i=1; i<=10; i++) {
            log.info(String.valueOf(i));
            User u = new User();
            u.setId(i);
            String userName = new String("aaaaaaaaaaaaaa" + i);
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
