package com.umbrella.demo.apache.commons.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

/**
 * create by xudazhou 2018/10/29
 */
public class IOUtilsDemo {

    /**
     * 空串
     * 创建一个空文件
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        InputStream is = IOUtils.toInputStream("", "utf-8");
        FileUtils.copyInputStreamToFile(is, new File("E:\\TDDOWNLOAD\\1.txt"));
    }

    /**
     * null
     * IOUtils 抛空指针异常
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        InputStream is = IOUtils.toInputStream(null, "utf-8");
        FileUtils.copyInputStreamToFile(is, new File("E:\\TDDOWNLOAD\\1.txt"));
    }
}
