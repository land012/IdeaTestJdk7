package com.umbrella.demo.java;

import org.junit.Test;

import java.nio.charset.Charset;

/**
 * Created by 大洲 on 14-12-8.
 */
public class CharsetDemo {

    @Test
    public void test1() {
        System.out.println(Charset.forName("gbk")); // GBK
    }
}
