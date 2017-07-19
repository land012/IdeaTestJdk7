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

        System.out.println(flag); // false

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

        System.out.println((System.currentTimeMillis() - start) / 1000); // 20
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
                br = new BufferedReader(new InputStreamReader(process.getErrorStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    log.error(line);
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
