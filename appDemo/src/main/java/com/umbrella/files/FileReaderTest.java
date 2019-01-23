package com.umbrella.files;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * create by xudazhou 2019/1/11
 */
public class FileReaderTest {

    /**
     * 当文件不存在时，会抛 java.io.FileNotFoundException: E:\TDDOWNLOAD\_temp\aa.txt (系统找不到指定的文件。)
     * @throws FileNotFoundException
     */
    @Test
    public void test1() throws FileNotFoundException {
        FileReader fr = new FileReader("E:\\TDDOWNLOAD\\_temp\\aa.txt");
    }
}
