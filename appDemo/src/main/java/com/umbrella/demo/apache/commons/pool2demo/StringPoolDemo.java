package com.umbrella.demo.apache.commons.pool2demo;

import com.umbrella.vo.User;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by xudazhou 2018/12/27
 * 线程池里只有两个对象，当第三个线程去拿时，拿不到对象，阻塞
 */
public class StringPoolDemo {

    private static Logger log = LoggerFactory.getLogger(StringPoolDemo.class);

    public static void main(String[] args) {
        GenericObjectPoolConfig cfg = new GenericObjectPoolConfig();
        cfg.setMaxTotal(2);

        GenericObjectPool<User> pool = new GenericObjectPool<>(new StringPoolFactory(), cfg);

        new Thread(new MyThread(pool)).start();
        new Thread(new MyThread(pool)).start();
        new Thread(new MyThread(pool)).start();
    }

    static class MyThread implements Runnable {

        private GenericObjectPool<User> pool;

        public MyThread(GenericObjectPool<User> pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            User str = null;
            try {
                String t = Thread.currentThread().getName();
                log.info("{} start!", t);
                str = pool.borrowObject();
                log.info("{} {}", t, str);

                Thread.sleep(5 * 1000);
                log.info("{} end!", t);
            } catch (Exception e) {
                log.error("ex=", e);
            } finally {
                if (str != null) {
                    pool.returnObject(str);
                }
            }
        }
    }
}
