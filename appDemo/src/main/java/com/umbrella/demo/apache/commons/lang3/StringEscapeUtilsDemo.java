package com.umbrella.demo.apache.commons.lang3;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
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
        System.out.println(StringEscapeUtils.escapeJava(":")); // \u3002
        System.out.println(StringEscapeUtils.unescapeJava("\u63A8\u5168\u6743\u9650"));
    }

    /**
     * 获取汉字的 html字符实体表示法
     */
    @Test
    public void test1_1() {
        System.out.println(StringEscapeUtils.escapeJava("电信")); // \u7535\u4FE1
        System.out.println(0x7535); // 30005
        System.out.println("=================================================");

        String str1 = "汉";
        System.out.println("html:" + getHtmlCharEntity(str1));  // html:&#27721;
    }

    public static String getHtmlCharEntity(String str1) {
        String unicode = StringEscapeUtils.escapeJava(str1);
        System.out.println(unicode); // \u6C49
        unicode = unicode.substring(2);
        System.out.println(unicode); // 6C49
        System.out.println(0x6C49); // 27721
        int i1 = Integer.valueOf(unicode, 16);
        System.out.println(i1); // 27721
        String charentity = "&#" + String.valueOf(i1) + ";";
        System.out.println(charentity);  // &#27721;
        return charentity;
    }

    /**
     * html
     */
    @Test
    public void test2() {
        String str1 = "你&好 > < = \" _";
        System.out.println(StringEscapeUtils.escapeHtml4(str1)); // 你&amp;好 &gt; &lt; = &quot;
        System.out.println(StringEscapeUtils.escapeHtml3(str1)); // 你&amp;好 &gt; &lt; = &quot;
        System.out.println(StringEscapeUtils.escapeHtml4(":")); // :
        System.out.println(org.apache.commons.lang.StringEscapeUtils.escapeHtml(":")); // :
    }

    @Test
    public void test3() {
        System.out.println(StringEscapeUtils.unescapeHtml4("&#124;")); // |
        System.out.println(StringEscapeUtils.unescapeHtml4("&#58;")); // :
    }

    /**
     * 中文转 unicode
     */
    @Test
    public void test4() {
        // Unicode编码
//		System.out.println(StringEscapeUtils.escapeJava("你好"));           // \u4F60\u597D
//		System.out.println(StringEscapeUtils.escapeEcmaScript("你好"));     // \u4F60\u597D
//		System.out.println(StringEscapeUtils.unescapeJava("\u4F60\u597D")); // 你好
//
//		System.out.println(StringEscapeUtils.ESCAPE_XML.translate("你好&")); // 你好&amp;
//		System.out.println(StringEscapeUtils.escapeXml("你好&"));            // 你好&amp;
//		System.out.println(StringEscapeUtils.escapeHtml3("你好&"));          // 你好&amp;
//		System.out.println(StringEscapeUtils.escapeHtml4("你好&"));          // 你好&amp;
//
//		System.out.println(org.apache.commons.lang.StringEscapeUtils.escapeHtml("你好")); // &#20320;&#22909;

        System.out.println(StringEscapeUtils.escapeJava("射雕ZERO")); // \u5C04\u96D5ZERO
        System.out.println(StringEscapeUtils.escapeJava("汤姆")); // \u6C64\u59C6
    }
}
