package com.umbrella.demo.java.net;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by 大洲 on 15-6-18.
 */
public class URLEncoderDemo {
    @Test
    public void test1() {
        try {
            System.out.println(URLEncoder.encode("哈哈", "utf-8")); // %E5%93%88%E5%93%88
            System.out.println(URLEncoder.encode(":", "utf-8")); // %3A
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws Exception {
        System.out.println(URLDecoder.decode("%3D", "utf-8")); // =
        System.out.println(URLDecoder.decode("%2B", "utf-8")); // +
        System.out.println(URLDecoder.decode("%2B", "utf-8")); // +
        System.out.println(URLDecoder.decode("%28%29", "utf-8")); // ()
    }
}
