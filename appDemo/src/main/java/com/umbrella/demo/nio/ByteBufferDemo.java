package com.umbrella.demo.nio;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by xudazhou on 2017/3/26.
 */
public class ByteBufferDemo {

    @Test
    public void test1() throws Exception {
        ByteBuffer bb1 = ByteBuffer.allocate(3);
        bb1.putChar('中');
        System.out.println(Arrays.toString(bb1.array())); // [78, 45, 0]
        System.out.println(Arrays.toString("中".getBytes("utf-8"))); // [-28, -72, -83]
    }
}
