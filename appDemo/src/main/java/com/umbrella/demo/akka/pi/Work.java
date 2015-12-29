package com.umbrella.demo.akka.pi;

/**
 * Created by xudazhou on 2015/12/29.
 * 任务
 */
public class Work {
    private final int start;
    private final int numOfElements;

    Work(int start, int numOfElements) {
        this.start = start;
        this.numOfElements = numOfElements;
    }

    public int getStart() {
        return start;
    }

    public int getNumOfElements() {
        return numOfElements;
    }
}
