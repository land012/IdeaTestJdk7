package com.umbrella.demo.java.text;

import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by xudazhou on 2017/6/15.
 */
public class DecimalFormatDemo {

    @Test
    public void test1() throws ParseException {
        DecimalFormat df = new DecimalFormat();
        long i1 = (long)df.parse("1,123");
        System.out.println(i1); // 1123
    }
}
