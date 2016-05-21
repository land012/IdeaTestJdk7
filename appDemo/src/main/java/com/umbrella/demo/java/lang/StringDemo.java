package com.umbrella.demo.java.lang;

import com.umbrella.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by 大洲 on 15-4-20.
 */
public class StringDemo {
    @Test
    public void test1() {
        System.out.println("abc".contains("a")); // true
        System.out.println("abc".contains("A")); // false
        System.out.println(StringUtils.containsIgnoreCase("abc", "a")); // true
        System.out.println(StringUtils.containsIgnoreCase("abc", "A")); // true
    }

    /**
     * 占位符
     */
    @Test
    public void test2() {
        System.out.println(String.format("Hello %s", "Elric")); // Hello Elric
        System.out.println(String.format("Hello {1}", "Orochimaru")); // Hello {1}
        System.out.println(String.format("%1$s is %1$s", "Mikasa")); // Mikasa is Mikasa
        User u1 = null;
        System.out.println(String.format("user1=%s", u1));
    }

    /**
     * 占位符
     */
    @Test
    public void test3() {
        System.out.println(String.format("%3d", 1)); // 左补空格
        System.out.println(String.format("%3d", 1234));
        System.out.println(String.format("%-3d", 1)); // 左对齐， 不足补空格
        System.out.println(String.format("%-3d", 1234));
        System.out.println(String.format("%+d", 12)); // +12
        System.out.println(String.format("%+d", -12)); // -12
        System.out.println(String.format("%05d", 123456)); // 123456
        System.out.println(String.format("%05d", 1234)); // 01234
    }

    /**
     * 编码问题
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        String str1 = "亲亲袋鼠-VINCI";
        System.out.println("str1=" + str1);
        System.out.println("str1.length()=" + str1.length()); // 10 如果文件是gbk编码，这个长度返回 13！！！！？？？
        for(int i=0; i<str1.length(); i++) {
            char c1 = str1.charAt(i);
            System.out.print(c1);
//            System.out.print(Character.to);
            System.out.println();
        }
        System.out.println("str1.getBytes(\"utf-8\").length=" + str1.getBytes("utf-8").length); // 18 中文三字节 英文一字节
        System.out.println("str1.getBytes(\"gbk\").length=" + str1.getBytes("gbk").length);   // 14 中文两字节 英文一字节
    }

    @Test
    public void test4_2() throws Exception {
        String str1 = "哈";
        System.out.println(str1.getBytes("GBK").length); // 2
        System.out.println(str1.getBytes("UTF-8").length); // 3
    }

    /**
     * append 对 null不会抛异常
     */
    @Test
    public void test5() {
        String str1 = null;
        StringBuilder str = new StringBuilder();
        str.append(str1);
        str.append("a");
        System.out.println(str); // nulla
    }
}
