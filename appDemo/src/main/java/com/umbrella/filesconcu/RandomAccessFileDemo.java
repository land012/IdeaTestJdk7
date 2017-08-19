package com.umbrella.filesconcu;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by xudazhou on 2015/8/25.
 * 并发访问文件
 */
public class RandomAccessFileDemo {

    private static final Logger log = Logger.getLogger(RandomAccessFileDemo.class);

    public static void main(String[] args) {
        new Thread(new WriteFileThread("aaaaa", "bbbbb")).start();
        new Thread(new WriteFileThread("cccc", "dddd")).start();
    }

    /**
     * 写文件
     */
    @Test
    public void test1() {
        try {
//            File f1 = new File(this.getClass().getResource("/").getPath(), "file2.txt");
//            log.info(f1.getAbsolutePath());
//            BufferedWriter bw = new BufferedWriter(new FileWriter(f1));
//            bw.write("hello world");
//            bw.flush();
//            bw.close();


        } catch (Exception e) {
            log.info("", e);
        }
    }

}
