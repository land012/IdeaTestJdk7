package com.umbrella.concurrent.executors;

import com.umbrella.vo.User;
import org.apache.log4j.Logger;

import java.util.concurrent.*;

/**
 * Created by 大洲 on 15-5-28.
 * ArrayBlockingQueue 使用指定容量大小的队列
 * 当任务数超过队列大小时，会抛异常 Exception in thread "main" java.util.concurrent.RejectedExecutionException
 * 导致主线程无法继续执行，但子线程仍可以执行
 * 但子线程消费了任务，主线程也不会继续添加任务了
 *
 */
public class Demo2 {
    private static final Logger log = Logger.getLogger(Demo1.class);

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000));

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
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
