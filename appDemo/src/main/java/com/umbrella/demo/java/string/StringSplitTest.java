package com.umbrella.demo.java.string;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2016/9/8.
 */
public class StringSplitTest {

    private static final Logger log = LoggerFactory.getLogger(StringSplitTest.class);

    /**
     * 会保留分隔符前面和中间的空串，不会保留最后的几个空子串
     */
    @Test
    public void testSplit_1() {
        String str1 = ",,bcc, ddc ,,,d,,";
        String[] arr1 = str1.split(",");
		/*
		 * 2016-09-08 20:31:38,992 INFO [main] StringTest.testSplit_1(259) |
		 * 2016-09-08 20:31:38,996 INFO [main] StringTest.testSplit_1(259) | b
		 * 2016-09-08 20:31:38,996 INFO [main] StringTest.testSplit_1(259) | c
		 * 2016-09-08 20:31:38,996 INFO [main] StringTest.testSplit_1(259) | d
		 */
		System.out.println(arr1.length);
        for(String s : arr1) {
            log.info("【{}】", s);
        }

    }

    /**
     * 分隔符分隔后
     * 如果数组的最后几个元素是空串，那么不会保留这些元素
     */
    @Test
    public void testSplit_2() {
        // split 当分隔符后面是空时，得到的数据长度
        String str1 = "k1=";
        String[] arr1 = str1.split("=");
        System.out.println(arr1.length); // 1

        // NullPointException
        String str2 = null;
        String[] arr2 = str2.split("_");
        System.out.println(arr2.length);
        for(int i=0; i<arr2.length; i++) {
            System.out.println("arr2[" + i + "]=" + arr2[i]);
        }
    }

    /**
     * 空串处理
     * 字符串首尾都有空串，最后的空串不会保留
     * ┃||a|b|||c┃
     */
    @Test
    public void testSplit_3() {
        String str1 = ",,a,b,,,c,,,"; // 9个逗号，应该返回10长度
        String[] arr1 = str1.split(",");
        System.out.println(arr1.length); // 7
        System.out.print("┃");
        for(int i=0; i<arr1.length; i++) {
            if(i==0) {
                System.out.print(arr1[i]);
            } else {
                System.out.print("|" + arr1[i]);
            }
        }
        System.out.println("┃");
    }
}
