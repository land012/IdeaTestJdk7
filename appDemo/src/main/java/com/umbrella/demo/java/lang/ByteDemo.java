package com.umbrella.demo.java.lang;

import org.junit.Test;

/**
 * Created by xudazhou on 2016/11/25.
 */
public class ByteDemo {
    @Test
    public void test1() {
        Byte b1 = 1;
        Integer i1 = 2;
        b1 = i1.byteValue();
        System.out.println(b1);
    }
}
