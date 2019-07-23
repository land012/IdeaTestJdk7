package com.umbrella.demo.apache.commons.net;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;

import java.io.IOException;

/**
 * create by xudazhou 2019/1/23
 */
public class FtpClientDemo {

    @Test
    public void test1() {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("192.168.186.3", 21);
            ftpClient.login("ftp", "ftp");

            FTPFile[] f1 = ftpClient.listDirectories();
            System.out.println("f1=" + f1);
            System.out.println("f1.length=" + f1.length);
            /*
             * dir1
             */
            for (FTPFile f : f1) {
                System.out.println(f.getName());
            }
            System.out.println("============================================");
            FTPFile[] f2 = ftpClient.listFiles();
            /*
             * dir1
             * proftpd-1.3.6.tar.gz
             */
            for (FTPFile f : f2) {
                System.out.println(f.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
