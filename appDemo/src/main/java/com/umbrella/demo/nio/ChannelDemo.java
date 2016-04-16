package com.umbrella.demo.nio;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

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

    /**
     * ServerSocketChannel
     */
    @Test
    public void test2() {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            server.socket().bind(new InetSocketAddress(9999));
            while (true) {
                SocketChannel client = server.accept();
                ByteBuffer buf = ByteBuffer.allocate(64);
                int i = client.read(buf);
                while (i!=-1) {
                    buf.flip();
                    while (buf.hasRemaining()) {
                        log.info((char)buf.get() + "");
                    }
                    buf.clear();
                    i = client.read(buf);
                }
                break;
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @Test
    public void test3() {
        try {
            SocketChannel client = SocketChannel.open();
            client.connect(new InetSocketAddress("127.0.0.1", 9999));
            ByteBuffer buf = ByteBuffer.allocate(64);
            buf.clear();
            buf.put("a".getBytes());
            buf.flip();
            while (buf.hasRemaining()) {
                client.write(buf);
            }
            client.close();
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
