package com.umbrella.files;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/**
 * Created by xudazhou on 2015/8/25.
 * 并发访问文件
 * tryLock 非阻塞式
 */
public class RandomAccessFileDemo2 {

    private static final Logger log = Logger.getLogger(RandomAccessFileDemo2.class);

    public static void main(String[] args) {
        new Thread(new WriteFileThread("11111", "22222")).start();
        new Thread(new WriteFileThread("333", "444")).start();
    }

    static class WriteFileThread implements Runnable {

        private String str1;
        private String str2;

        public WriteFileThread(String str1, String str2) {
            this.str1 = str1;
            this.str2 = str2;
        }

        @Override
        public void run() {
            writeFile(str1, str2);
        }

        private void writeFile(String str1, String str2) {
            try {
                String parentPath = ClassLoader.getSystemResource("").getPath();
                log.info("parrent path=" + parentPath); // /D:/_idea/TestApp/appDemo/target/classes/
                RandomAccessFile file1 = new RandomAccessFile(new File(parentPath, "file2.txt"), "rw");
                FileChannel fc = file1.getChannel();
                FileLock fl = null;
                log.info("尝试获取锁");
                while (true) {
                    try {
                        fl = fc.tryLock();
                        break;
                    } catch (OverlappingFileLockException e) {
                        log.info("文件被占用，等待......");
                        Thread.sleep(1000);
                    }
                }

                log.info("获取到锁，写入文件......");
                file1.seek(file1.length()); // 跳至文件结尾开始写文件，否则会覆盖原文件内容
                BufferedWriter bw = new BufferedWriter(Channels.newWriter(fc, "utf-8"));
                if(file1.length()>0) bw.newLine();
                bw.write(str1);
                Thread.sleep(5000);
                bw.newLine();
                bw.write(str2);
                log.info("写入文件结束");
                bw.flush();
//            bw.close();
                fl.release();
                fc.close();
                file1.close();
            } catch (Exception e) {
                log.info("", e);
            }
        }
    }

}
