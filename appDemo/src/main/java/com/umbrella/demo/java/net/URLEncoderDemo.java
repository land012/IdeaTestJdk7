package com.umbrella.demo.java.net;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 大洲 on 15-6-18.
 */
public class URLEncoderDemo {
    @Test
    public void test1() {
        try {
            System.out.println(URLEncoder.encode("哈哈", "utf-8")); // %E5%93%88%E5%93%88
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
