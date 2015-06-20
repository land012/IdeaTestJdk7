package com.umbrella.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

/**
 * Created by 大洲 on 15-6-18.
 */
public class ConvertUtilTest {
    @Test
    public void testGetUnicode1() {
        String str = "你好";
        System.out.println(ConvertUtil.getUnicode(str)); // \u4F60\u597D
        System.out.println(StringEscapeUtils.escapeJava(str)); // \u4F60\u597D

        String str1 = "=";
        System.out.println(ConvertUtil.getUnicode(str1)); // \u3D
        System.out.println(StringEscapeUtils.escapeJava(str1)); // =

        String str2 = "abc";
        System.out.println(ConvertUtil.getUnicode(str2)); // \u61\u62\u63
        System.out.println(StringEscapeUtils.escapeJava(str2)); // abc
    }
}
