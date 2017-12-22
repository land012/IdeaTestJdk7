package cf.umbrella.chrome;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xudazhou on 2017/11/22.
 */
public class Bookmarks {

    /**
     *
     * 读取乱码的 chrome 书签文件
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        String filep = "E:\\TDDOWNLOAD\\_重做系统备份\\Bookmarks";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filep), "utf-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    @Test
    public void test2() {
        File dir1 = new File("F:\\ccc");
//        String[] files = dir1.list();
//        System.out.println(files.length);
        File[] fileArr = dir1.listFiles();
        System.out.println(fileArr.length);
        List<File> fileList = Arrays.asList(fileArr);
        fileList.stream().forEach((f) -> {
            try {
                System.out.println(f.getCanonicalPath() + " begin");
                String line = null;
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "gbk"));
                while ((line = br.readLine()) != null) {
                    if (line.indexOf("www.bjgaj.gov.cn") > -1) {
                        System.out.println(line);
                        System.out.println(f.getCanonicalPath());
                        System.exit(0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        });
    }
}
