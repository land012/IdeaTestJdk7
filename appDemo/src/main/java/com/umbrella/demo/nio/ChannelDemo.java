package com.umbrella.demo.nio;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by xudazhou on 2016/4/7.
 */
public class ChannelDemo {

    private static final Logger log = LoggerFactory.getLogger(ChannelDemo.class);

    /**
     * 文件是 utf-8 编码
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        RandomAccessFile raFile = new RandomAccessFile("E:\\TDDOWNLOAD\\1.txt", "rw");
        FileChannel fileChannel = raFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(2);
        int i = fileChannel.read(buf);
        while (i!=-1) {
            // 换行符占两个字节
            log.info("read length " + i);
            buf.flip();
            while (buf.hasRemaining()) {
                log.info((char) buf.get() + "");
            }
            buf.clear();
            i = fileChannel.read(buf);
        }
        raFile.close();
    }
}
