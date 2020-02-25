package com.umbrella.demo.java.lang;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * create by xudazhou 2019/1/4
 */
public class ByteOpeDemo {

    /**
     * 各类型的字节数
     */
    @Test
    public void test1() {
        System.out.println(Integer.SIZE / 8);
        System.out.println(Byte.SIZE / 8);
    }

    @Test
    public void testLongtoBytes() {
        long l1 = 2323L;
        ByteBuffer bb = ByteBuffer.allocate(Long.SIZE / 8);
        bb.putLong(l1);
        byte[] bytes = bb.array();
    }

    /**
     *
     */
    @Test
    public void testBytestoLong() {
        long l1 = 2323L;
        ByteBuffer bb = ByteBuffer.allocate(Long.SIZE / 8);
        bb.putLong(l1);
        byte[] bytes = bb.array();
        ByteBuffer bb2 = ByteBuffer.wrap(bytes);
        System.out.println(bb2.getLong()); // 2323
    }
}
