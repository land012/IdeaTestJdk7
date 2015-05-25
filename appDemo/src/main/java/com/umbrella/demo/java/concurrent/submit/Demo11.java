package com.umbrella.demo.java.concurrent.submit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 大洲 on 15-2-12.
 * execute + Runnable
 * 子线程会抛出异常，但是主线程拿不到
 */
public class Demo11 {
    public static void main(String[] args) {
        Demo11 demo = new Demo11();
        demo.test2();
        System.out.println("this is main");
    }


    /**
     * 子线程会抛出异常，但是主线程拿不到
     */
    private void test2() {
        try {
            ExecutorService exec = Executors.newFixedThreadPool(2);
            for(int i=0; i<2; i++) {
                exec.execute(new Work(i));
            }
            exec.shutdown();
        } catch (Exception e) {
            System.out.println("=============================" + e);
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
