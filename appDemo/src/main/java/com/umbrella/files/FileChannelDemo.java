package com.umbrella.files;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/**
 * 并发操作文件 保证文件一致性
 * Created by xudazhou on 2015/8/26.
 *
 * FileOutputStream 的 FileChannel
 */
public class FileChannelDemo {

    private static final Logger log = Logger.getLogger(FileChannelDemo.class);

    public static void main(String[] args) {
        new Thread(new WriteFileThread("eee", "fff")).start();
        new Thread(new WriteFileThread("ggg", "hhhh")).start();
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
                FileOutputStream fos = new FileOutputStream(new File(ClassLoader.getSystemResource("").getPath(), "file1.txt"), true);
                FileChannel fc = fos.getChannel();
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
                fos.write("\n".getBytes("utf-8"));
                fos.write(str1.getBytes("utf-8"));
                Thread.sleep(5000);
                fos.write("\n".getBytes("utf-8"));
                fos.write(str2.getBytes("utf-8"));
                log.info("写入文件结束");
                fl.release();
                fc.close();
                fos.close();
            } catch (Exception e) {
                log.info("", e);
            }
        }
    }
}
