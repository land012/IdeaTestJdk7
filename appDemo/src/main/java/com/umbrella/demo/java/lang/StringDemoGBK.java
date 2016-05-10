package com.umbrella.demo.java.lang;

import org.junit.Test;

/**
 * Created by xudazhou on 2016/5/8.
 * 工程编码是 utf-8
 * 文件编码是gbk
 */
public class StringDemoGBK {
    @Test
    public void test1() {
        String str1 = "亲亲袋鼠-VINCI";
        System.out.println("str1.length()=" + str1.length()); // 13 ?????为啥是 13??
        for(int i=0; i<str1.length(); i++) {
            System.out.print(str1.charAt(i));
            System.out.print(",");
        }
        System.out.println();
    }
}
