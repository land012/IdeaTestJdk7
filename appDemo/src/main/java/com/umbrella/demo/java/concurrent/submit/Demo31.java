package com.umbrella.demo.java.concurrent.submit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by 大洲 on 15-2-12.
 * submit + Callable
 * 主线程可以拿到子线程的异常
 */
public class Demo31 {
    public static void main(String[] args) {
        Demo31 demo = new Demo31();
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
            List<Future<Integer>> list = new ArrayList<Future<Integer>>();
            for(int i=0; i<2; i++) {
                list.add(exec.submit(new Work(i)));
            }
            for(Future f : list) {
                System.out.println("++++++++++++++++++++" + f.get()); // 会抛出子线程中的异常
            }
            exec.shutdown(); // 当子线程抛异常时，不会执行到这一步，所以异常不能再下面捕捉，要在上面捕捉
        } catch (Exception e) {
            System.out.println("=================" + e);
        }
    }

    private class Work implements Callable<Integer> {
        private int id;

        private Work(int id) {
            this.id = id;
        }

        @Override
        public Integer call() throws Exception {
            if(id==1) throw new RuntimeException("here is an exception");
            return id;
        }
    }
}
