package com.umbrella.demo.concurrent.submit;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by 大洲 on 15-2-12.
 * submit + Runnable
 * 不抛异常的情况！！！！！！！！！！！！！！！！！
 */
public class ExecutorsDemo22 {

    private static Logger log = Logger.getLogger(ExecutorsDemo22.class);

    public static void main(String[] args) {
        ExecutorsDemo22 demo = new ExecutorsDemo22();
        demo.test1();
        System.out.println("this is main");
    }

    /**
     * submit的方式
     * 主线程可以拿到子线程的异常
     */
    private void test1() {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        try {
            List<Future> list = new ArrayList<Future>();
            for(int i=0; i<2; i++) {
                list.add(exec.submit(new Work(i)));
            }
            for(Future f : list) {
                Object o = f.get(); // 不抛异常的话，会返回 null
                Work r = (Work)o;
                r.getId(); // java.lang.NullPointerException
                System.out.println("future.get()=" + o); // null
            }
        } catch (Exception e) {
            log.info("=================", e);
        } finally {
            exec.shutdown();
        }
    }

    private class Work implements Runnable {
        private int id;

        private Work(int id) {
            this.id = id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public void run() {
            System.out.println("here is sub thread, id=" + id);
        }
    }
}
