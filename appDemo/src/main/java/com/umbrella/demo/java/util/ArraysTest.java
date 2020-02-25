package com.umbrella.demo.java.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

        int[] arr2 = Arrays.copyOf(arr1, arr1.length-1);
        System.out.println("arr2[]=" + Arrays.toString(arr2));
        Arrays.sort(arr2);
        // arr1 不受影响
        System.out.println("arr1[]=" + Arrays.toString(arr1));
        System.out.println("sorted arr2[]=" + Arrays.toString(arr2));
        // 不会包括 2
        int[] arr3 = Arrays.copyOfRange(arr1, 1, 2);
        System.out.println("arr3[]=" + Arrays.toString(arr3));

    }

    /**
     * copyOf
     */
    @Test
    public void test2_2() {
        char[] value = {'a', 'b', 'c'};
        value = Arrays.copyOf(value, 5);
        System.out.println(value.length); // 5
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

    /**
     * asList
     */
    @Test
    public void test4() {
        String[] strArr1 = { "a", "b", "c" };
        List<String> list1 = Arrays.asList(strArr1);
        System.out.println(list1); // [a, b, c]
    }

    /**
     * null 安全
     * 返回 字符串 null
     */
    @Test
    public void test5_null() {
        String[] arr = null;
        String str1 = Arrays.toString(arr);
        if (null == str1) {
            System.out.println("null is null");
        } else if (StringUtils.equals("null", str1)) {
            System.out.println("null:" + str1); // null:null
        } else {
            System.out.println("else:" + str1);
        }
    }

    @Test
    public void test6() {
        double[] arr = new double[5];
        System.out.println(arr[0]); // 0.0
    }
}
