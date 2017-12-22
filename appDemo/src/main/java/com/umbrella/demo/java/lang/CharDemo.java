package com.umbrella.demo.java.lang;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by xudazhou on 2017/3/26.
 */
public class CharDemo {

    @Test
    public void test1() {
        char c1 = '中';
        System.out.println(c1); // 中
        Character ct1 = new Character(c1);
        System.out.println(ct1.toString().getBytes().length); // 3
    }

    /**
     * 判断是否是汉字
     */
    @Test
    public void test2() {
        System.out.println(isChinese('汉')); // true
        System.out.println(isChinese('b')); // false
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    @Test
    public void test3() throws Exception {
        String str1 = "\\x66\\x65\\x65\\x64\\x5f\\x70\\x72\\x6f\\x64\\x75\\x63\\x74\\x69\\x6f\\x6e\\x5f\\x73\\x68\\x6f\\x75\\x62\\x61\\x69\\x5f\\x6e\\x6f\\x6e\\x6e\\x65\\x77\\x73\\x5f\\x75\\x6e\\x69\\x71\\x5f\\x62\\x6c\\x6f\\x6f\\x6d\\x2e\\x79\\x6d\\x6c";
//        System.out.println(str1);
        String str2 = str1.replace("\\x", "%");
//        System.out.println(str2);
        System.out.println(URLDecoder.decode(str2, "utf-8")); // feed_production_shoubai_nonnews_uniq_bloom.yml
    }
}
