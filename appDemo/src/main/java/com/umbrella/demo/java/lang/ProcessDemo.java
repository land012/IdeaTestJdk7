package com.umbrella.demo.java.lang;

import org.junit.Test;

import java.io.BufferedReader;
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
//        pb.directory(new File("."));
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
}
