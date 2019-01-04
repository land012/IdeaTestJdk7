package com.umbrella.demo.apache.commons;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

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
     * 不会保留空子串
     * 长度为1的数组
     */
    @Test
    public void test4() {
        String str1 = "aasd,";
        String[] str1Arr = StringUtils.split(str1, ",");
        /*
         * -aasd-
         */
        for(int i=0; i<str1Arr.length; i++) {
            System.out.println("-" + str1Arr[i] + "-");
        }

        String[] arr2 = str1.split(",");
        System.out.println(arr2.length);  // 1
    }

    /**
     * 不会保留空子串
     * 返回长度为2的数组
     *
     */
    @Test
    public void test5() {
        String str1 = ",,aasd,,,bb,,";
        String[] strArr1 = StringUtils.split(str1, ",");
        log.info("strArr1.length={}", strArr1.length); // 2
        // 【aasd】【bb】
        for(int i=0; i<strArr1.length; i++) {
            if (i == strArr1.length - 1) {
                System.out.println("【" + strArr1[i] + "】");
            } else {
                System.out.print("【" + strArr1[i] + "】");
            }
        }

        String[] strArr2 = StringUtils.splitPreserveAllTokens(str1, ",");
        log.info("strArr2.length={}", strArr2.length); // 8
        // 【】【】【aasd】【】【】【bb】【】【】
        for (int i=0; i<strArr2.length; i++) {
            if (i == strArr2.length - 1) {
                System.out.println("【" + strArr2[i] + "】");
            } else {
                System.out.print("【" + strArr2[i] + "】");
            }
        }

    }

    /**
     * 不会保留空子串
     * 长度为0的数组
     */
    @Test
    public void test6() {
        String str1 = ",";
        String[] str1Arr = StringUtils.split(str1, ",");
        System.out.println(str1Arr.length); // 0
        for(int i=0; i<str1Arr.length; i++) {
            System.out.println("-" + str1Arr[i] + "-");
        }
    }

    /**
     * split limit
     * 分 limit-1 次，分成 limit  段
     */
    @Test
    public void test7() {
        String str1 = "a|b|c";
        System.out.println(str1.split("|", 1).length); // 1
        System.out.println(StringUtils.split(str1, "|", 1).length); // 1
        System.out.println(str1.split("|", 2).length); // 分一次，分成两段
        System.out.println(StringUtils.split(str1, "|", 2).length); // 2

        System.out.println(StringUtils.split(str1, "|", 5).length); // 3

        System.out.println(Arrays.toString(StringUtils.split(str1, "|", 2)));
    }

    /**
     * 把 字符串 分隔符 分别作为 字符分割符
     */
    @Test
    public void test8() {
        String str1 = "w1aw2bw3abw4";
        System.out.println(Arrays.toString(StringUtils.split(str1, "ab"))); // [w1, w2, w3, w4]
        System.out.println(Arrays.toString(StringUtils.splitPreserveAllTokens(str1, "ab"))); // [w1, w2, w3, , w4]
        System.out.println(Arrays.toString(StringUtils.splitByWholeSeparator(str1, "ab"))); // [w1aw2bw3, w4]

        String str2 = "a\nb\rc";
        System.out.println(Arrays.toString(StringUtils.split(str2, "\r\n"))); // [a, b, c]
        /*
         * [a
         * c]
         */
        System.out.println(Arrays.toString(StringUtils.splitByWholeSeparator(str2, "\r\n")));
    }

}
