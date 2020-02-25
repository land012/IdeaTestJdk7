package com.umbrella.demo.java.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * create by xudazhou 2019/7/28
 */
public class DownStream {

    @Test
    public void test1() throws Exception {
        File f1 = new File("E:\\TDDOWNLOAD\\1.f4v");
        FileOutputStream fos = new FileOutputStream(f1);

        URL url = new URL("https://v-3cd21027.71edge"
                + ".com/videos/v1/20190529/75/b3/7265c9377d562541e0e2d740faf29111.f4v?key"
                + "=06c9d1ffc73c57e63487ff91ebc6e1fb7&dis_k=2fc92e4a0b4f35bcbc3816d186258645d&dis_t=1564307424&dis_dz"
                + "=CNIX-BeiJing&dis_st=103&src=iqiyi"
                + ".com&uuid=7750b113-5d3d6fe0-1db&qd_vipdyn=0&pv=0.1&client=119.80.177.27&su"
                + "=e09ec0a17069ebf0d1edec1683b59fec&retry=1&mi"
                + "=tv_2592914800_2592914800_769a9331848afb6330541e7d241ec6ec&qyid=e09ec0a17069ebf0d1edec1683b59fec"
                + "&qd_uid=1014550909&qd_tm=1564290984893&e=&cross-domain=1&ct=2&qd_tvid=2592914800&qd_aid=2592914800"
                + "&qd_stert=0&qypid=2592914800_02020031010000000000&qd_p=7750b112&qd_src=01010031010000000000&qd_k"
                + "=853df8f8e7ce672a29540770409c957c&qd_index=1&qd_vip=0&bt=&z=beijing2_cnix&qd_vipres=0&tn"
                + "=0.02746620645438691");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        InputStream is = conn.getInputStream();

        byte[] buf = new byte[1024];
        int i = -1;
        while ((i = is.read(buf)) != -1) {
            fos.write(buf, 0 , i);
        }

        is.close();
        fos.close();
    }
}
