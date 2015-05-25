package com.umbrella.util;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by 大洲 on 14-11-14.
 */
public class ZipDemo {
    public static void main(String[] args) {
//        fn2();
        fn4();
    }

    /**
     * 从一个压缩文件，复制到另一个压缩文件
     */
    public static void fn1() {
        try {
            ZipFile zf = new ZipFile("E:\\TDDOWNLOAD\\1111.zip", Charset.forName("gbk"));
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("E:\\TDDOWNLOAD\\2222.zip"));
            Enumeration<ZipEntry> enums = (Enumeration<ZipEntry>)zf.entries();
            while (enums.hasMoreElements()) {
                ZipEntry zeo = enums.nextElement();
                InputStream is = zf.getInputStream(zeo);
                ZipEntry zen = new ZipEntry(zeo.getName());
                zos.putNextEntry(zen);
                byte[] buf = new byte[1024];
                int l = -1;
                while((l=is.read(buf))!=-1) {
                    zos.write(buf, 0, l);
                }
                zos.closeEntry();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件流生成 Zip
     */
    public static void fn2() {
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream("E:\\TDDOWNLOAD\\1111.zip"), Charset.forName("gbk"));
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("E:\\TDDOWNLOAD\\2222.zip"));
            ZipEntry zeo = zis.getNextEntry(); // 拿到第一个压缩文件
            while (zeo != null) {
                ZipEntry zen = new ZipEntry(zeo.getName());
                zos.putNextEntry(zen);
                int l = -1;
                byte[] buf = new byte[1024];
                while ((l=zis.read(buf))!=-1) {
                    zos.write(buf, 0, l);
                }
                zos.closeEntry();
                zis.closeEntry();
                zeo = zis.getNextEntry();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载 zip 文件
     */
    public static void fn4() {
        String url = "http://localhost:9999/web1/test1_writeZip.do";
        try {
            URL requrl = new URL(url);
            URLConnection conn = requrl.openConnection();
            conn.setRequestProperty("Cookie", "ckk1=v1; ckk2=v2"); // 这种方式可以提交 Cookie
            conn.setDoInput(true);
            conn.setDoOutput(false);
            ZipInputStream zis = new ZipInputStream(conn.getInputStream(), Charset.forName("gbk")); // 流里是压缩包的多个文件，没有压缩包的概念
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("E:\\TDDOWNLOAD\\2222.zip"));
            ZipEntry zeo = zis.getNextEntry(); // 拿到第一个压缩文件
            while (zeo != null) {
                ZipEntry zen = new ZipEntry(zeo.getName());
                zos.putNextEntry(zen);
                int l = -1;
                byte[] buf = new byte[1024];
                while ((l=zis.read(buf))!=-1) {
                    zos.write(buf, 0, l);
                }
                zos.closeEntry();
                zis.closeEntry();
                zeo = zis.getNextEntry();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
