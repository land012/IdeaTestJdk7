package com.umbrella.demo.java.concurrent;

import java.util.concurrent.Callable;

/**
 * Created by 大洲 on 15-1-15.
 */
public class CallableTask implements Callable<String> {
    private long id;

    public CallableTask(long id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        System.out.println("this is CallableTask " + id);
        if(true) throw new RuntimeException("出异常了");
        return null;
    }
}
