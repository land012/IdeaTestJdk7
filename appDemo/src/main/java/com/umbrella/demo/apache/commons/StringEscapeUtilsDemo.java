package com.umbrella.demo.apache.commons;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

/**
 * Created by 大洲 on 15-6-18.
 */
public class StringEscapeUtilsDemo {
    @Test
    public void test1() {
        System.out.println(StringEscapeUtils.escapeJava("你好")); // \u4F60\u597D
        System.out.println(StringEscapeUtils.escapeJava("=")); // =
        System.out.println(StringEscapeUtils.escapeJava(".")); // .
        System.out.println(StringEscapeUtils.escapeJava("。")); // \u3002
    }
}
