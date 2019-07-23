package com.umbrella.files;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * create by xudazhou 2018/12/20
 */
public class FilePathDemo {

    /**
     *
     */
    @Test
    public void test5() {
        System.out.println(File.separator); // \ 路径分隔符
        System.out.println(File.pathSeparator); // ; 环境变量路径分隔符
    }

    /**
     * 当前目录
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        File f = new File(".");
        System.out.println(f.getCanonicalPath()); // D:\_idea2017\TestApp\appDemo
        System.out.println(f.getAbsolutePath()); // D:\_idea2017\TestApp\appDemo\.
        System.out.println(f.getPath()); // .
    }

    /**
     * 绝对路径
     * @throws IOException
     */
    @Test
    public void testPath1() throws IOException {
        File f2 = new File("E:\\TDDOWNLOAD\\_temp\\1.png");
        System.out.println(f2.getName()); // 1.png
        System.out.println(f2.getPath()); // E:\TDDOWNLOAD\_temp\1.png
        System.out.println(f2.getAbsolutePath());
        System.out.println(f2.getCanonicalPath());
    }

    /**
     * 相对路径
     * @throws IOException
     */
    @Test
    public void testPath2() throws IOException {
        File f2 = new File(".\\files\\1.txt");
        System.out.println(f2.getName()); // 1.png
        System.out.println(f2); // .\files\1.txt
        System.out.println(f2.getPath()); // .\files\1.txt
        System.out.println(f2.getAbsolutePath()); // D:\_idea2017\TestApp\appDemo\.\files\1.txt
        System.out.println(f2.getCanonicalPath()); // D:\_idea2017\TestApp\appDemo\files\1.txt
    }

    /**
     * 相对路径
     * @throws IOException
     */
    @Test
    public void testPath3() throws IOException {
        File f2 = new File("..\\files\\1.txt");
        System.out.println(f2.getName()); // 1.png
        System.out.println(f2.getPath()); // ..\files\1.txt
        System.out.println(f2.getAbsolutePath()); // D:\_idea2017\TestApp\appDemo\..\files\1.txt
        System.out.println(f2.getCanonicalPath()); // D:\_idea2017\TestApp\files\1.txt
    }
}
