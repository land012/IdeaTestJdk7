package com.umbrella.demo.java.lang;

import org.junit.Test;

/**
 * Created by 大洲 on 15-4-24.
 */
public class BooleanDemo {

    private boolean defaultBool;

    @Test
    public void test1() {
        System.out.println(new BooleanDemo().defaultBool); // false
    }

    /**
     * boolean 数组默认值 false
     */
    @Test
    public void test2() {
        boolean[] arr1 = new boolean[3];
        for(boolean b : arr1) {
            System.out.println(b); // false
        }
    }

    /**
     * 声明的 boolean 变量的默认值
     * 不初始化不能使用
     */
    @Test
    public void test3() {
        boolean b1;
//        System.out.println(b1); // Variable 'b1' might not have bean intialized
    }
}
