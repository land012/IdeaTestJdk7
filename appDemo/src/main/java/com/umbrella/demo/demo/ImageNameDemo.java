package com.umbrella.demo.demo;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Created by xudazhou on 2016/8/16.
 */
public class ImageNameDemo {
    @Test
    public void test2() {
        DecimalFormat df = new DecimalFormat("000");
        for(int i=1; i<=225; i++) {
            System.out.println("<img src=\"images/" + df.format(i) + ".jpg\" />");
            System.out.println("<hr />");
        }
    }

    @Test
    public void test3() {
        for(int i=1; i<=775; i++) {
            System.out.println("<img src=\"images/" + i + ".jpg\" />");
            System.out.println("<hr />");
        }
    }
}
