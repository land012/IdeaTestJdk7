package com.umbrella.demo.java.lang;

import org.junit.Test;

/**
 * Created by xudazhou on 2016/11/25.
 */
public class ByteDemo {

    /**
     * int 转 byte
     */
    @Test
    public void test1() {
        Byte b1 = 1;
        Integer i1 = 2;
        b1 = i1.byteValue();
        System.out.println(b1); // 2
    }

    @Test
    public void test2() throws Exception {
        String str1 = "你";
        String str2 = "好";
        byte[] bytes1 = str1.getBytes("utf-8");
        byte[] bytes2 = str2.getBytes("utf-8");
        byte[] bytes3 = new byte[bytes1.length + bytes2.length];
        int j = 0;
        for (int i=0; i < bytes3.length; i++) {
            if (i < bytes1.length) {
                bytes3[i] = bytes1[i];
            } else {
                bytes3[i] = bytes2[j];
                j++;
            }
        }
        String str3 = new String(bytes3, "utf-8");
        System.out.println(str3); // 你好
    }

    @Test
    public void test3() {
        byte b1 = (byte) 0b11111111;
        int i1 = b1;
        System.out.println(i1); // -1
        System.out.println(0b11111111); // 255
        System.out.println(b1); // -1

        byte b2 = (byte) 0b10000001; // 第一位是符号位，并当成补码处理
        System.out.println(b2); // -127

        byte b3 = (byte) 0b10000000;
        System.out.println(b3); // -128

        byte b4 = (byte) 0b100000000;
        System.out.println(b4); // 0 被截断了
    }
}
