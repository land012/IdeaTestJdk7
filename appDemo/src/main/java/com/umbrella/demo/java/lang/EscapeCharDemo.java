package com.umbrella.demo.java.lang;

import org.junit.Test;

/**
 * 转义字符
 * Created by xudazhou on 2015/12/11.
 */
public class EscapeCharDemo {
    @Test
    public void test1() {
        String str1 = "a\012b";
        System.out.println(str1);
        String str11 = "a\010b";
        System.out.println(str11);
        String str2 = "c\rd"; // 回车(CR) ，将当前位置移到本行开头
        System.out.println(str2);
        String str3 = "e\nf";
        System.out.println(str3);
    }
}
