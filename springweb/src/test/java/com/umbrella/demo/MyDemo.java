package com.umbrella.demo;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

/**
 * Created by xudazhou on 2015/11/23.
 */
public class MyDemo {
    @Test
    public void test1() {
        System.out.println("哈哈"); //
        /*
         * 打印的编码是错的
         * 如果文件使用 gbk编码就没问题
         */
        System.out.println(StringEscapeUtils.escapeJava("哈哈")); // \u935D\u581D\u6431
        System.out.println(StringEscapeUtils.unescapeJava("\\u54C8\\u54C8")); // 哈哈
    }
}
