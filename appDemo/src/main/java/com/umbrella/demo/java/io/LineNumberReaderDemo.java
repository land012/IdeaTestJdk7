package com.umbrella.demo.java.io;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * create by xudazhou 2018/12/29
 */
public class LineNumberReaderDemo {

    /**
     * 行号从 0 开始，读取一行后，行号自动 +1
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader("../files/1.txt"));
        System.out.println(lnr.getLineNumber()); // 0
        System.out.println(lnr.readLine()); // aa
        System.out.println(lnr.getLineNumber()); // 1
        lnr.skip(Long.MAX_VALUE);
        System.out.println(lnr.getLineNumber()); // 2
        lnr.close();
    }

    @Test
    public void test2() throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader("../files/3.txt"));
        lnr.skip(Long.MAX_VALUE);
        System.out.println(lnr.getLineNumber()); // 0
        lnr.close();
    }
}
