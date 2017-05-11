package com.umbrella.demo.java.io;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by xudazhou on 2017/5/5.
 */
public class ByteArrayOutputStreamDemo {

    /**
     * ByteArrayOutputStream 的 toString()
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(baos, "ascii");
        osw.write("中文");
        osw.flush();
        System.out.println(baos.toString());
    }
}
