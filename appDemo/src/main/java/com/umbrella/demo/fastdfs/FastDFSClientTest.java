package com.umbrella.demo.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * FastDFS 测试
 * Created by xudazhou on 2015/12/6.
 * 不需要配置启动 nginx 也能上传下载
 */
public class FastDFSClientTest {
    /**
     * txt 文件下载
     */
    @Test
    public void test1() throws Exception {
        String classpath = ClassLoader.getSystemResource("").getPath();
        ClientGlobal.init(classpath + "fdfs_client.conf");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient1 client = new StorageClient1(trackerServer, null);

        String fileId = "group1/M00/00/00/wKi6A1ZmNHKANkmoAAAAA0BlMA0432.txt";
        byte[] buf = client.download_file1(fileId);
        FileOutputStream fos = new FileOutputStream("E:\\TDDOWNLOAD\\ccc.txt");
        fos.write(buf);
        fos.close();
        trackerServer.close();
    }

    /**
     * 文件下载
     */
    @Test
    public void test2() throws Exception {
        String classpath = ClassLoader.getSystemResource("").getPath();
        ClientGlobal.init(classpath + "fdfs_client.conf");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient1 client = new StorageClient1(trackerServer, null);

        String fileId = "group1/M00/00/00/wKi6A1ZjC6GAHxcGAAALz5XiTfs388.jar";
        byte[] buf = client.download_file1(fileId);
        FileOutputStream fos = new FileOutputStream("E:\\TDDOWNLOAD\\haha.jar");
        fos.write(buf);
        fos.close();
        trackerServer.close();
    }
}
