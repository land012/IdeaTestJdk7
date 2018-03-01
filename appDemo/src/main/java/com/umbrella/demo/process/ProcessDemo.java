package com.umbrella.demo.process;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xudazhou on 2017/3/31.
 */
public class ProcessDemo {

    /**
     * 测试 exitCode
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void test1() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder();
        List<String> commands = new ArrayList<>();
        commands.add("ping");
        commands.add("127.0.0.1");
        pb.command(commands);

//        pb.command("ping 127.0.0.1"); // 这种方式会失败 java.io.IOException: Cannot run program "ping 127.0.0.1"

        pb.directory(new File("."));
        Process process = pb.start();

        // java.lang.IllegalThreadStateException: process has not exited
//        System.out.println(process.exitValue());
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
        String line = null;
        System.out.println("==================== begin ==========================");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("==================== end ==========================");
        System.out.println("waitfor=" + process.waitFor()); // 0
        int e1 = process.exitValue();
        int e2 = process.exitValue();
        System.out.println("exitCode=" + e1); // 0
        System.out.println("exitCode=" + e2); // 0
    }

    /**
     * 不读输出，直接调用 waitFor
     * 当执行次数比较少时，可以通过
     * 当执行次数比较多时，程序会阻塞，因为缓冲区被写满，无法继续写入，程序阻塞在 waitFor
     * @throws Exception
     */
    @Test
    public void test1_1() throws Exception {
        ProcessBuilder pb = new ProcessBuilder();
        List<String> commands = new ArrayList<>();
        commands.add("ping");
        commands.add("-n");
        commands.add("80");
        commands.add("127.0.0.1");
        pb.command(commands);

        long start = System.currentTimeMillis();
        System.out.println("begin = " + start);

        Process process = pb.start();

        System.out.println("waitfor=" + process.waitFor()); // 0
        int e1 = process.exitValue();
        System.out.println("exitCode=" + e1); // 0

        long cost = System.currentTimeMillis() - start;
        System.out.println(cost / 1000.0); // 30.797s
    }

    /**
     * 读输出，调用 waitFor，可以通过
     * @throws Exception
     */
    @Test
    public void test1_2() throws Exception {
        ProcessBuilder pb = new ProcessBuilder();
        List<String> commands = new ArrayList<>();
        commands.add("ping");
        commands.add("-n");
        commands.add("80");
        commands.add("127.0.0.1");
        pb.command(commands);

        long start = System.currentTimeMillis();
        System.out.println("begin = " + start);

        Process process = pb.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        System.out.println("waitfor=" + process.waitFor()); // 0
        int e1 = process.exitValue();
        System.out.println("exitCode=" + e1); // 0

        long cost = System.currentTimeMillis() - start;
        System.out.println(cost / 1000.0); // 79.81s
    }

    /**
     * 测试 exitCode
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void test2() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder();
        List<String> commands = new ArrayList<>();
        commands.add("cmd");
        commands.add("/c");
        commands.add("dir");
        pb.command(commands);
        pb.directory(new File("."));
        Process process = pb.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
        String line = null;
        System.out.println("==================== begin ==========================");
        /**
         *  驱动器 D 中的卷是 D盘
         卷的序列号是 6C97-9E44

         D:\_idea\TestApp 的目录

         2017/05/06  22:41    <DIR>          .
         2017/05/06  22:41    <DIR>          ..
         2016/05/16  14:17                54 .gitignore
         2015/06/26  15:20    <DIR>          .idea
         2017/05/09  14:55    <DIR>          abacihadoop
         2017/02/10  20:53    <DIR>          appDemo
         2017/01/20  14:26    <DIR>          common-domain
         2017/01/20  14:26    <DIR>          common-util
         2017/01/20  14:26    <DIR>          demo
         2015/12/27  19:18    <DIR>          fastdfs-client-java
         2017/01/20  14:26    <DIR>          hello-idea-service
         2017/05/06  22:41             9,904 pom.xml
         2017/03/06  20:00    <DIR>          protobuf-demo
         2017/02/10  20:53    <DIR>          springweb
         2017/01/20  14:26    <DIR>          target
         2017/02/10  21:04             9,507 TestApp.iml
         2017/01/20  14:26    <DIR>          web
         2017/01/20  14:26    <DIR>          webservice-client
         2017/01/20  14:26    <DIR>          webservice-server
         2017/01/20  14:26    <DIR>          WebServiceInterface
         3 个文件         19,465 字节
         17 个目录 55,345,598,464 可用字节
         */
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("==================== end ==========================");
        int e1 = process.exitValue();
        System.out.println("exitCode=" + e1); // 0
    }
}
