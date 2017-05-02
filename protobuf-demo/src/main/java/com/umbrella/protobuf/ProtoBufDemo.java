package com.umbrella.protobuf;

import com.google.protobuf.ByteString;
import org.junit.Test;

/**
 * Created by xudazhou on 2017/3/17.
 */
public class ProtoBufDemo {

    @Test
    public void test1() throws Exception {
        byte[] bytes = "你".getBytes("gb18030");
        ByteString bs1 = ByteString.copyFrom(bytes);
        System.out.println(bs1.size()); // 2
    }

    @Test
    public void test2() throws Exception {
        String str1 = new String("我");
        String str2 = new String("我");
        byte[] bytes1 = str1.getBytes("utf-8");
        byte[] bytes2 = str2.getBytes("utf-8");
        System.out.println(bytes1 == bytes2); // false
        System.out.println(bytes1.equals(bytes2)); // false

        ByteString bs1 = ByteString.copyFrom(bytes1);
        ByteString bs2 = ByteString.copyFrom(bytes2);
        System.out.println(bs1.equals(bs2)); // true
    }
}
