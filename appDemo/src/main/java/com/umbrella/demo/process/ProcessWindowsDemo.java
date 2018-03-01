package com.umbrella.demo.process;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2017/7/19.
 */
public class ProcessWindowsDemo {

    private static Logger log = LoggerFactory.getLogger(ProcessWindowsDemo.class);

    @Test
    public void test1() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("D:\\_wsh\\helloworld.bat");
        Process process = pb.start();
        /**
         * %cd% # java工程目录
         * D:\_idea\TestApp
         *
         * %~dp0 # 脚本所在目录
         * D:\_wsh\
         */
        System.out.println(getProcessResult(process.getInputStream(), "utf-8"));
        System.out.println("=================================");
        /**
         * 'xxx' 不是内部或外部命令，也不是可运行的程序
         * 或批处理文件。
         */
        System.out.println(getProcessResult(process.getErrorStream(), "gbk"));

        // 脚本出错时，返回 1
        System.out.println(process.waitFor()); // 1
        System.out.println(process.exitValue()); // 1
    }

    public static String getProcessResult(InputStream inputStream, String charset) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset));
        String line = null;
        StringBuilder res = new StringBuilder();
        while ((line = br.readLine()) != null) {
            res.append(line);
            res.append("\n");
        }
        br.close();
        return res.toString();
    }

    @Test
    public void test2() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("D:\\_wsh\\fordemo.bat");
        Process process = pb.start();
        System.out.println(getProcessResult(process.getInputStream(), "utf-8"));
    }

    /**
     * 脚本默认执行20秒
     *
     * 指定 waitFor 5秒，
     * 如果 5秒内，脚本结束，waitFor 返回 true
     * 否则 waitFor 返回 false，
     */
    @Test
    public void test3() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("D:\\_wsh\\fordemo.bat");
        Process process = pb.start();

        new Thread(new ErrorWorker(process)).start();
        FutureTask<String> ft = new FutureTask(new Worker(process));
        new Thread(ft).start();

        long start = System.currentTimeMillis();

        boolean flag = process.waitFor(5, TimeUnit.SECONDS);

        System.out.println("waitfor=" + flag); // false

        if (flag) {
            System.out.println(ft.get());
        } else {
            process.destroy();
            /*
             * 返回部分结果:
              * 1
              * 2
             */
            System.out.println(ft.get());
        }

        System.out.println("exitcode=" + process.exitValue()); // 1，因为被 destory 掉，所以返回 1

        System.out.println((System.currentTimeMillis() - start) / 1000); // 8, 正常 20s
    }

    /**
     * 进程未结束就调 exitValue，抛异常
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("D:\\_wsh\\fordemo.bat");
        Process process = pb.start();

        new Thread(new ErrorWorker(process)).start();
        FutureTask<String> ft = new FutureTask(new Worker(process));
        new Thread(ft).start();

        long start = System.currentTimeMillis();

        /*
         * 直接调用 exitValue，返回异常
         * java.lang.IllegalThreadStateException: process has not exited
         */
        System.out.println("exitcode=" + process.exitValue());

        System.out.println((System.currentTimeMillis() - start) / 1000); // 正常 20s
    }

    /**
     * 当调用 waitfor 时，会一直等待，直到子进程结束
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("D:\\_wsh\\fordemo.bat");
        Process process = pb.start();

        new Thread(new ErrorWorker(process)).start();
        FutureTask<String> ft = new FutureTask(new Worker(process));
        new Thread(ft).start();

        long start = System.currentTimeMillis();

        System.out.println("isalive=" + process.isAlive()); // true
        System.out.println("waitcode=" + process.waitFor()); // 0
        System.out.println("exitcode=" + process.exitValue()); // 0
        System.out.println("isalive=" + process.isAlive()); // false

        System.out.println((System.currentTimeMillis() - start) / 1000); // 21
    }

    /**
     * 等待的时长超过了子进程执行时长，但子进程失败了???
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("D:\\_wsh\\fordemo_fail.bat");
        Process process = pb.start();

        new Thread(new ErrorWorker(process)).start();
        FutureTask<String> ft = new FutureTask(new Worker(process));
        new Thread(ft).start();

        long start = System.currentTimeMillis();

        System.out.println("isalive=" + process.isAlive()); // true
        // 这个返回 只标识等待了这么久，子进程是否结束，不代表只进程是否成功
        System.out.println("waitcode=" + process.waitFor(50, TimeUnit.SECONDS)); // true
        System.out.println("exitcode=" + process.exitValue()); // 1
        System.out.println("isalive=" + process.isAlive()); // false

        System.out.println((System.currentTimeMillis() - start) / 1000); // 21
    }

    class Worker implements Callable<String> {

        private Process process;

        public Worker(Process process) {
            this.process = process;
        }

        @Override
        public String call() throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder res = new StringBuilder();
            while ((line = br.readLine()) != null) {
                res.append(line);
                res.append("\n");
            }
            br.close();
            return res.toString();
        }
    }

    class ErrorWorker implements Runnable {

        private Process process;

        public ErrorWorker(Process process) {
            this.process = process;
        }

        @Override
        public void run() {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(process.getErrorStream(), "gbk"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    log.error(line);
                    if (line.length() > 10000) {
                        process.destroy();
                        break;
                    }
                }
            } catch (Exception e) {
                log.error("", e);
            } finally {
                try {
                    if (br == null) {
                        br.close();
                    }
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
    }

}
