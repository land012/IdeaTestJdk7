package com.umbrella.demo.java.string;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;


/**
 * Created by xudazhou on 2016/5/8.
 */
public class StringDemoUTF8 {
    @Test
    public void test1() {
        String str1 = "亲亲袋鼠-VINCI";
        System.out.println(str1.length()); // 10
    }

    /**
     * 编码
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        String str1 = "小a";
        System.out.println(Arrays.toString(str1.getBytes("utf-8"))); // [-27, -80, -113, 97]
        System.out.println(Arrays.toString(str1.getBytes("gbk"))); // [-48, -95, 97]
        System.out.println(Arrays.toString(str1.getBytes("gb18030"))); // [-48, -95, 97]
    }

    /**
     * 没啥卵用
     * @throws UnsupportedEncodingException
     */
    @Test
    public void test3() throws UnsupportedEncodingException {
        String str1 = "哈哈";
        byte[] byte1 = str1.getBytes("utf-8");
        byte[] byte2 = new String(byte1, "utf-8").getBytes("gbk");
        String str2 = new String(byte2, "gbk");
        System.out.println(str2); // 哈哈
    }
}
