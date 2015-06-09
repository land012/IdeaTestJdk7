package com.umbrella.grammar.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by 大洲 on 15-6-6.
 */
public class MyClient {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("192.168.0.104", 8888);
            InputStream is = client.getInputStream();
            byte[] buf = new byte[1024];
            int i = -1;
            StringBuilder res = new StringBuilder();
            while((i=is.read(buf))!=-1) {
                res.append(new String(buf, 0, i));
            }
            System.out.println(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
