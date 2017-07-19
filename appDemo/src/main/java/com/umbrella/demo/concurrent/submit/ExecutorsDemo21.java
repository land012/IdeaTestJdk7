package com.umbrella.demo.concurrent.submit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by 大洲 on 15-2-12.
 * submit + Runnable
 * 这种组合的唯一作用似乎就是：主线程能拿到子线程的抛出的异常
 * 抛异常的情况！！！！！！！！！！！！！！！！！
 * 主线程可以拿到子线程的异常
 */
public class ExecutorsDemo21 {
    public static void main(String[] args) {
        ExecutorsDemo21 demo = new ExecutorsDemo21();
        demo.test1();
        System.out.println("this is main");
    }

    /**
     * submit的方式
     * 主线程可以拿到子线程的异常
     */
    private void test1() {
        try {
            ExecutorService exec = Executors.newFixedThreadPool(2);
            List<Future> list = new ArrayList<Future>();
            for(int i=0; i<3; i++) {
                list.add(exec.submit(new Work(i)));
            }
            for(Future f : list) {
                f.get(); // 会抛出子线程中的异常
            }
        } catch (Exception e) {
            System.out.println("=================" + e);
        }
    }

    private class Work implements Runnable {
        private int id;

        private Work(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            if(id==1) throw new RuntimeException("here is an exception");
        }
    }
}
