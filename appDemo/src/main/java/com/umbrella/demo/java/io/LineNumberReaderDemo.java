package com.umbrella.demo.java.io;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * create by xudazhou 2018/12/29
 */
public class LineNumberReaderDemo {

    @Test
    public void test1() throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader("../files/1.txt"));
        System.out.println(lnr.getLineNumber()); // 0
        lnr.skip(Long.MAX_VALUE);
        System.out.println(lnr.getLineNumber()); // 2
        lnr.close();
    }
}
