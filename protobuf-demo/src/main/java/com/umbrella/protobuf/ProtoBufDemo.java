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
}
