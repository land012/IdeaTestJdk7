package com.umbrella.grammar.jdk7;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * jdk7新特性
 * @author asdf
 *
 */
public class Demo {

    /**
     * @param args
     */
    public static void main(String[] args) {
        /**
         * switch 支持 String
         */
        String gender = "male";
        switch(gender) {
            case "male":
                System.out.println("male");
                break;
            case "female":
                System.out.println("female");
                break;
            default:
                System.out.println("other");
                break;
        }

        /**
         * 变长参数
         */
        System.out.println("============== 变长参数 ===============");
        fn1(1,2,3,4);

        fn2();
        List<String> list = new ArrayList<String>();
        fn2(list);
        list.add("a");
        fn2(list);
        list.add("b");
        fn2(list);
    }

    public static void fn1(int... args) {
        for(int i : args) {
            System.out.print(i + ",");
        }
        System.out.println();
    }

    @SafeVarargs
    public static <T> void fn2(T... args) {
        System.out.println(args.length);
    }

    /**
     * 一个catch， 多个异常
     */
    public void fn3() {
        try {
            Integer.parseInt("aa");
            URLDecoder.decode("aa", "utf-8");
        } catch(NumberFormatException | UnsupportedEncodingException e) {

        }
    }

    /**
     * 二进制表示
     */
    @Test
    public void test1() {
        /**
         * 二进制表示
         */
        int i1 = 0b1010; // 10
        int i2 = 123_456;
        System.out.println(i1);
        System.out.println(i2);
    }

}
