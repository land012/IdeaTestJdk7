package com.umbrella.demo.akka.concur;

/**
 * Created by xudazhou on 2015/12/26.
 */
public class MyCounter {
    private int i;

    public MyCounter(int i) {
        this.i = i;
    }

    public void incr() {
        i++;
    }

    public int get() {
        return i;
    }

    @Override
    public String toString() {
        return "MyCounter{" +
                "i=" + i +
                '}';
    }

}
