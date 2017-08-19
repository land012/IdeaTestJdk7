package com.umbrella.filesconcu;

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
 * 使用 RandomAccessFile 处理文件
 * lock() 阻塞式，似乎没有阻塞，直接抛出了异常
 */
public class WriteFileThread implements Runnable {

    private static final Logger log = Logger.getLogger(WriteFileThread.class);

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
            RandomAccessFile file1 = new RandomAccessFile(new File(ClassLoader.getSystemResource("").getPath(), "file1.txt"), "rw");
            FileChannel fc = file1.getChannel();
            FileLock fl = null;
            log.info("尝试获取锁");
            while (true) {
                try {
                    fl = fc.lock();
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
            // 这里不能 close，否则会抛异常
//            bw.close();
            fl.release();
            fc.close();
            file1.close();
        } catch (Exception e) {
            log.info("", e);
        }
    }
}
