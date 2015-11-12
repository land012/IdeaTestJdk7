package com.umbrella.demo.concurrent.submit.demo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 大洲 on 15-1-15.
 * 子线程抛异常对主线程的影响
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(1);

        /**
         * 会抛出异常，但不影响后面任务的执行
         */
        for(int i=0; i<3; i++) {
            exec.execute(new Task(i));
        }

        /**
         * 不会抛出异常，正常执行下面的任务
         */
//        for(int i=0; i<3; i++) {
//            exec.submit(new Task(i));
//        }

//        for(int i=0; i<3; i++) {
//            exec.submit(new CallableTask(i));
//        }

        exec.shutdown();
    }
}
