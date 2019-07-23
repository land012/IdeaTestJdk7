package com.umbrella.demo.apache.commons.io;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * create by xudazhou 2019/5/13
 */
public class FileUtilsTest {

    private static Logger log = LoggerFactory.getLogger(FileUtilsTest.class);

    /**
     * 删除目录
     * @throws IOException
     */
    @Test
    public void test1() throws Exception {
        FileUtils.deleteDirectory(new File("E:\\TDDOWNLOAD\\temp1"));
        log.info("delete finsh");
        Thread.sleep(30 * 1000);
    }

    /**
     * jvm结束时，删除目录
     * @throws IOException
     */
    @Test
    public void test2() throws Exception {
        FileUtils.forceDeleteOnExit(new File("E:\\TDDOWNLOAD\\temp1"));
        log.info("delete finsh");
        Thread.sleep(30 * 1000);
    }
}
