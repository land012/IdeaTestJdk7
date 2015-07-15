package com.umbrella.demo.java.util;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by 大洲 on 15-7-5.
 */
public class ArraysTest {

    /**
     * 对数组排序
     */
    @Test
    public void test1() {
        int[] arr1 = new int[3];
        arr1[0] = 2;
        arr1[1] = 1;
        arr1[2] = 3;
        System.out.println("arr1[]=" + Arrays.toString(arr1));
        Arrays.sort(arr1);
        System.out.println("sorted arr1[]=" + Arrays.toString(arr1));
    }

    /**
     * 拷贝数组
     */
    @Test
    public void test2() {
        int[] arr1 = new int[3];
        arr1[0] = 2;
        arr1[1] = 1;
        arr1[2] = 3;
        System.out.println("arr1[]=" + Arrays.toString(arr1));

        int[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Arrays.sort(arr2);
        System.out.println("arr1[]=" + Arrays.toString(arr1));
        System.out.println("sorted arr2[]=" + Arrays.toString(arr2));
    }

    /**
     * 传引用
     */
    @Test
    public void test3() {
        int[] arr1 = new int[3];
        arr1[0] = 2;
        arr1[1] = 1;
        arr1[2] = 3;
        System.out.println("arr1[]=" + Arrays.toString(arr1)); // arr1[]=[2, 1, 3]
        sortArr(arr1);
        // arr1 被排序了
        System.out.println("arr1[]=" + Arrays.toString(arr1)); // arr1[]=[1, 2, 3]
    }

    private void sortArr(int[] arr) {
        Arrays.sort(arr);
    }
}
