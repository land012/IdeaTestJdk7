package com.umbrella.algorithm.ludo;

import org.junit.Test;

/**
 * Created by 大洲 on 15-7-5.
 */
public class DemoTest {
    /**
     * LudoType 比较
     */
    @Test
    public void test1() {
        System.out.println(LudoType.compare(LudoType.AAA, LudoType.CAD)); // 1
        System.out.println(LudoType.compare(LudoType.AAA, LudoType.AAA)); // 0
        System.out.println(LudoType.compare(LudoType.AAB, LudoType.ABC)); // -1
    }

    /**
     * 判断是不是顺子
     */
    @Test
    public void test2() {
        int[] arr1 = { 1, 2, 3, 4 };
        int[] arr2 = { 1, 2, 2, 3 };
        int[] arr3 = { 1, 2, 4, 3 };
        int[] arr4 = { 1, 2, 4, 5 };
        int[] arr5 = { 1 };
        System.out.println("arr1=" + MainDemo.isJunko(arr1));
        System.out.println("arr2=" + MainDemo.isJunko(arr2));
        System.out.println("arr3=" + MainDemo.isJunko(arr3));
        System.out.println("arr4=" + MainDemo.isJunko(arr4));
        System.out.println("arr5=" + MainDemo.isJunko(arr5));
    }
}
