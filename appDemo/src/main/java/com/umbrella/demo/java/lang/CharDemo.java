package com.umbrella.demo.java.lang;

import org.junit.Test;

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
}
