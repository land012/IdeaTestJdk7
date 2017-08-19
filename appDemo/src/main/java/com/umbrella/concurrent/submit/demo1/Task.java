package com.umbrella.concurrent.submit.demo1;

/**
 * Created by 大洲 on 15-1-15.
 */
public class Task implements Runnable {
    private long id;

    public Task(long id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("this is task " + id);
        throw new RuntimeException("出异常了");
    }
}
