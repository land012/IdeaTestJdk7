package com.umbrella.demo.java.regexp;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 大洲 on 15-4-20.
 */
public class RegExpDemo {
    @Test
    public void test1() {
        System.out.println(Pattern.matches("a|b|c", "adef")); // false
        System.out.println(Pattern.matches("^a.*", "adef")); // true
        System.out.println(Pattern.compile("a|b|c").matcher("adef").matches()); // false
        System.out.println(Pattern.compile("^a.*").matcher("adef").matches()); // true
        System.out.println(Pattern.compile("a|b|c").matcher("adef").find()); // true
    }

    @Test
    public void test2() {
        Pattern pattern = Pattern.compile("a|b|c");
        Matcher matcher = pattern.matcher("adecgfb");
        /**
         * a
         * c
         * b
         */
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test3() {
        Pattern pattern = Pattern.compile("a|b|c");
        Matcher matcher = pattern.matcher("adecgfb");
        // java.lang.IllegalStateException: No match found
        System.out.println(matcher.group());
    }
}
