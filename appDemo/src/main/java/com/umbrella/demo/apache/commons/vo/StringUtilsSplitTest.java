package com.umbrella.demo.apache.commons.vo;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2016/9/8.
 */
public class StringUtilsSplitTest {

    private static final Logger log = LoggerFactory.getLogger(StringUtilsSplitTest.class);

    /**
     * 分割函数
     */
    @Test
    public void test1() {
        String str2 = "a,b,c";
		String[] arr1 = StringUtils.split(str2, ",");
		for(String s : arr1) {
            log.info(s);
		}

		// 不会抛异常
		String[] arr2 = StringUtils.split(null, ",");
        if(arr2==null) {
            log.info("arr2 is null");
        } else {
            log.info(arr2.length + "");
        }
    }

    /**
     * split()
     * 子串的前后空格会被会保留
     * - aasd-
     * - 3sdf-
     * - df -
     * -23423 -
     */
    @Test
    public void test2() {
        String str1 = " aasd, 3sdf, df ,23423 ";
        String[] str1Arr = StringUtils.split(str1, ",");
        for(int i=0; i<str1Arr.length; i++) {
            System.out.println("-" + str1Arr[i] + "-");
        }
    }

    /**
     * 长度为1的数组
     * 没有分割符
     * 子串的前后空格会被会保留
     * - aasd-
     */
    @Test
    public void test3() {
        String str1 = " aasd";
        String[] str1Arr = StringUtils.split(str1, ",");
        for(int i=0; i<str1Arr.length; i++) {
            System.out.println("-" + str1Arr[i] + "-");
        }
    }

    /**
     * 长度为1的数组
     */
    @Test
    public void test4() {
        String str1 = "aasd,";
        String[] str1Arr = StringUtils.split(str1, ",");
        for(int i=0; i<str1Arr.length; i++) {
            System.out.println("-" + str1Arr[i] + "-");
        }
    }

    /**
     * 长度为2的数组
     */
    @Test
    public void test5() {
        String str1 = "aasd,,bb,";
        String[] str1Arr = StringUtils.split(str1, ",");
        for(int i=0; i<str1Arr.length; i++) {
            System.out.println("-" + str1Arr[i] + "-");
        }
    }

    /**
     * 长度为0的数组
     */
    @Test
    public void test6() {
        String str1 = ",";
        String[] str1Arr = StringUtils.split(str1, ",");
        for(int i=0; i<str1Arr.length; i++) {
            System.out.println("-" + str1Arr[i] + "-");
        }

    }
}
