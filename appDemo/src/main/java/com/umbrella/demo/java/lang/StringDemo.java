package com.umbrella.demo.java.lang;

import com.umbrella.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

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
}
