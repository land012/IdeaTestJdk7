package com.umbrella.math;

import org.junit.Test;

/**
 * Created by xudazhou on 2017/6/29.
 */
public class AverageDemo {

    /**
     * 把 count 平均分成 1000份
     * 如果平均值取整数，平均大小是 91，最后一个分片是 918
     */
    @Test
    public void test1() {
        int count = 91827;
        int avg = count / 1000;
        int lastSplit = 0;
        int curSplit = -1;
        for (int i = 1; i < 1000; i++) {
            curSplit = avg * i;
            System.out.println("len" + i + "=" + (curSplit - lastSplit));
            lastSplit = curSplit;
        }
        System.out.println(count - lastSplit);
    }

    /**
     * 用 double 做平均值，每个 split取整
     * 每个分片的大小是 91 或 92
     */
    @Test
    public void test2() {
        int count = 91827;
        double avg = count * 1.0 / 1000;
        System.out.println("avg=" + avg);
        double lastSplit = 0;
        double curSplit = -1;
        for (int i = 1; i < 1000; i++) {
            curSplit = Math.round(avg * i);
            System.out.println((int) curSplit);
//            System.out.println("len" + i + "=" + (curSplit - lastSplit));
            lastSplit = curSplit;
        }
        System.out.println(count - lastSplit);
    }

    @Test
    public void test3() {
        int count = 432;
        double avg = count / 1000d;
        double lastSplit = 0;
        double curSplit = -1;
        for (int i = 1; i < 1000; i++) {
            curSplit = Math.round(avg * i);
//            System.out.println("len" + i + "=" + (curSplit - lastSplit));
            System.out.println("curSplit=" + curSplit);
            lastSplit = curSplit;
        }
        System.out.println(count - lastSplit);
    }
}
