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

    /**
     * html
     */
    @Test
    public void test2() {
        String str1 = "你&好 > < = \" _";
        System.out.println(StringEscapeUtils.escapeHtml4(str1)); // 你&amp;好 &gt; &lt; = &quot;
        System.out.println(StringEscapeUtils.escapeHtml3(str1)); // 你&amp;好 &gt; &lt; = &quot;
    }

    @Test
    public void test3() {
        System.out.println(StringEscapeUtils.unescapeHtml4("&#124;")); // |
    }
}
