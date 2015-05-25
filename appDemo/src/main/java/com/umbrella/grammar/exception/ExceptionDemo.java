package com.umbrella.grammar.exception;

import org.junit.Test;

/**
 * Created by 大洲 on 15-1-7.
 */
public class ExceptionDemo {
    /**
     * try 语法，哪一句可以执行到
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.out.println(1); // 打印
            if(true)throw new RuntimeException("哈哈");
            System.out.println(2); // 不打印
        } finally {
            System.out.println(3); // 打印
        }
        System.out.println(4); // 不打印
    }

    /**
     * 怎样拿到 e.printStackTrace(); 的内容
     */
    @Test
    public void test2() {
        String res1 = "";
        String res2 = "";
        String res3 = "";
        String res4 = "";
        String res5 = "";
        try {
            String str = null;
            long l1 = Long.parseLong(str);
        } catch (NumberFormatException e) {
            res1 = e.toString();
            res2 = e.getLocalizedMessage();
            res3 = e.getMessage();
            StackTraceElement[] steArr = e.getStackTrace();
            for(StackTraceElement e1 : steArr) {
                res4 += e1.toString() + "\n";
            }
        }
        System.out.println("======================================");
        System.out.println(res1);
        System.out.println("======================================");
        System.out.println(res2);
        System.out.println("======================================");
        System.out.println(res3);
        System.out.println("======================================");
        System.out.println(res4);
        System.out.println("======================================");
        System.out.println(res5);
        System.out.println("======================================");
    }

    /**
     * 可以打印 return 后的内容
     */
    @Test
    public void test3() {
        try {
            System.out.println(1); // 打印
            return;
        } finally {
            System.out.println(2); // 打印
        }
    }
}
