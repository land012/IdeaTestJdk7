package com.umbrella.demo.java.lang;

import org.junit.Test;

/**
 * Created by xudazhou on 2015/8/3.
 */
public class MathDemo {

    /**
     * 角度与弧度
     */
    @Test
    public void test1() {
        double angle = 30.0; // 角度
        double radian = angle/180*Math.PI; // 弧度
        System.out.println(Math.sin(radian)); // 0.49999999999999994
        System.out.println(Math.sin(Math.toRadians(30.0))); // 0.49999999999999994
    }

    @Test
    public void test2() {
        System.out.println(Math.round(11.4534)); // 11
        System.out.println(Math.round(11.5334)); // 12
        System.out.println(Math.rint(11.4534)); // 11.0
        System.out.println(Math.rint(11.5334)); // 12.0
    }
}
