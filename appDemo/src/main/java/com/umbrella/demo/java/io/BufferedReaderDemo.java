package com.umbrella.demo.java.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by xudazhou on 2017/7/23.
 */
public class BufferedReaderDemo {

    /**
     * 不会因为 buffersize 大小，而影响读取完整的一行
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("E:\\TDDOWNLOAD\\1.txt"), 10);
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}
