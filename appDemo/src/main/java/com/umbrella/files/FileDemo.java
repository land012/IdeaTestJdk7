package com.umbrella.files;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by xudazhou on 2017/3/6.
 */
public class FileDemo {

    /**
     * 读取gbk编码的文件，打印会乱码
     * @throws Exception
     */
    @Test
    public void test1_readgbk() throws Exception {
        File f = new File("E:\\TDDOWNLOAD\\_temp\\aa.txt");
        System.out.println(f);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line = br.readLine();
        System.out.println(line.getBytes().length); // 20
        System.out.println(line.length()); // 12
        System.out.println(line);

    }

    /**
     * 读取gbk编码的文件，生成utf-8编码的文件
     * 生成了 utf-8的文件
     * @throws Exception
     */
    @Test
    public void test2_gen_file_charset() throws Exception {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("E:\\TDDOWNLOAD\\aa.txt"), "gbk");
        char[] buf = new char[1024];
        StringBuilder sb = new StringBuilder();
        int count = -1;
        while ((count = isr.read(buf)) != -1) {
            sb.append(buf, 0, count);
        }
        isr.close();

        PrintWriter pw = new PrintWriter("E:\\TDDOWNLOAD\\bb.txt", "utf-8");
        pw.write(sb.toString());
        pw.flush();
        pw.close();

        FileOutputStream fos = new FileOutputStream("E:\\TDDOWNLOAD\\cc.txt");
        fos.write(sb.toString().getBytes("utf-8"));
        fos.flush();
        fos.close();
    }

    /**
     * 生成指定编码的文件
     * @throws Exception
     */
    @Test
    public void test4_gen_file_charset() throws Exception {
        String str1 = "哈哈";
        FileOutputStream fos = new FileOutputStream("E:\\TDDOWNLOAD\\aa.txt");
        // 可以通过指定编码，输出相应编码的文本
        OutputStreamWriter osw = new OutputStreamWriter(fos, "gbk");
        osw.write(str1);
        osw.flush();
        osw.close();
    }

    /**
     * 读取utf-8编码的文件，打印不会乱码
     * 英文1字节，中文3字节
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("E:\\TDDOWNLOAD\\bb.txt"));
        String line = br.readLine();
        System.out.println(line.getBytes().length); // 14
        System.out.println(line.length()); // 10
        System.out.println(line); // this is 中文
        char c = line.charAt(9);
        System.out.println(c); // 文
    }

    /**
     * 向文件写入字节
     * 用 python 读
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        File f = new File("E:\\TDDOWNLOAD\\file1.dat");
        FileOutputStream fos = new FileOutputStream(f);

        String str1 = "hello xili, i am hector, how are you, what are you doing, nice to meet you, what is your name, how old are you, dasdkfesfdwekfjgid";
        String str2 = "你好";
        byte[] bytearr1 = str1.getBytes("utf-8");
        byte[] bytearr2 = str2.getBytes("utf-8");

        ByteBuffer bb = ByteBuffer.allocate(8 + bytearr1.length + bytearr2.length);
        bb.putInt(bytearr1.length);
        bb.put(bytearr1);
        bb.putInt(bytearr2.length);
        bb.put(bytearr2);

        fos.write(bb.array());

        fos.flush();
        fos.close();
    }

    /**
     * 向已存在文件中追加内容
     */
    @Test
    public void test7() throws Exception {
        File f = new File("E:\\TDDOWNLOAD\\test.txt");
        if (!f.exists()) {
            f.createNewFile();
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f, true), "gbk"));
        bw.write("a");
        bw.newLine();
        bw.write("bcd");
        bw.newLine();
        bw.flush();
        bw.close();
    }

    /**
     * test1.txt 不会创建
     * test2.txt 会创建
     * @throws Exception
     */
    @Test
    public void test8() throws Exception {
        File f = new File("E:\\TDDOWNLOAD\\test1.txt");

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\TDDOWNLOAD\\test2.txt"), "utf-8"));
        bw.close();
    }

    @Test
    public void test9() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("E:\\TDDOWNLOAD\\_temp\\1.txt"));
            String line = null;
            int i = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(i);
                System.out.println(StringUtils.isEmpty(line));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写文件，路径不存在，
     * 会报错
     */
    @Test
    public void testwrite() throws IOException {
        File dir = new File("E:\\TDDOWNLOAD\\_temp\\dir1");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        FileWriter fw = new FileWriter(new File(dir, "dir2\\1.txt"));
        fw.write("haha");
        fw.close();
    }

    /**
     * rename 可以 mv 文件
     */
    @Test
    public void testrename() {
        File f1 = new File("E:\\TDDOWNLOAD\\_temp\\1.png");
        System.out.println(f1.renameTo(new File("E:\\TDDOWNLOAD\\1.png")));

        File dir1 = new File("E:\\TDDOWNLOAD\\_temp\\dir1");
        System.out.println(dir1.renameTo(new File("E:\\TDDOWNLOAD\\dir1")));
    }

}
