package com.umbrella.demo.java.lang;

import com.umbrella.vo.User;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Created by 大洲 on 15-3-12.
 */
public class IntegerDemo {

    /**
     * Integer 对象比较
     */
    @Test
    public void test0_1() {
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        System.out.println(i1==i2); // false
        System.out.println(i1.equals(i2)); // true
    }

    /**
     * 对于 int 类型，不指定值时，默认为 0
     * 对于 Integer 类型，不指定值时，默认为 null
     */
    @Test
    public void test1() {
        User u1 = new User();
        System.out.println("id=" + u1.getId()); // 0
        System.out.println("age=" + u1.getAge()); // null
    }

    /**
     * 比较
     */
    @Test
    public void test2() {
        System.out.println(Integer.compare(1, 2)); // -1
    }

    @Test
    public void test3() {
        System.out.println(Integer.MAX_VALUE); // 2147483647
        // 无符号整数的最大值
        System.out.println(new DecimalFormat("0").format(Math.pow(2, 32)-1)); // 4294967295
    }

    /**
     * 转化为 十六进制
     * 补码表示
     */
    @Test
    public void test4() {
        System.out.println(Integer.toHexString(Integer.MAX_VALUE)); // 7fffffff
        int hashCode = "abcdsed".hashCode();
        System.out.println(Integer.toHexString(hashCode & 0xff)); // d0
        System.out.println(Integer.toHexString(10 & 0xff)); // a
        System.out.println(Integer.toHexString(7)); // 7
        System.out.println(Integer.toHexString(-7)); // fffffff9
    }

    /**
     * 二进制
     * 补码表示
     */
    @Test
    public void test4_1() {
        byte btye1 = 0b0010;
        System.out.println(Integer.toBinaryString(btye1)); // 10
        System.out.println(Integer.toBinaryString(-54));   // 11111111111111111111111111001010
        System.out.println(Integer.toBinaryString(-254));  // 11111111111111111111111100000010
    }

    /**
     * 十六进制 转 十进制
     */
    @Test
    public void test6() {
        System.out.println(0x66);
        System.out.println(0x99);
    }

    /**
     * int 类型的 bit数
     */
    @Test
    public void test5() {
        System.out.println(Integer.SIZE); // 32
        System.out.println(Character.SIZE);
        System.out.println(Byte.SIZE);
    }

}
