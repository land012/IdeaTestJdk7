package com.umbrella.concurrent.executors;

import com.umbrella.vo.User;
import org.apache.log4j.Logger;

import java.util.concurrent.*;

/**
 * Created by 大洲 on 15-5-28.
 * SynchronousQueue 队列
 * 当任务数超过可用线程数时，异常 java.util.concurrent.RejectedExecutionException
 */
public class Demo3SyncQueue {
    private static final Logger log = Logger.getLogger(Demo3SyncQueue.class);

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());

        for(int i=1; i<=1000; i++) {
            log.info(String.valueOf(i));
            User u = new User();
            u.setId(i);
            String userName = new String("aaaaaaaaaaaaaaa" + i);
            u.setUserName(userName);
            // java.util.concurrent.RejectedExecutionException
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
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
