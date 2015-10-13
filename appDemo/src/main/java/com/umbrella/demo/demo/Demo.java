package com.umbrella.demo.demo;

import com.umbrella.demo.json.jackson1.domain.Lion;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Created by 大洲 on 14-11-5.
 */
public class Demo {
    public static void main(String[] args) {
//        System.out.println("Hello World!");

//        String str1 = "abc";
//        str1 = StringUtils.trimToEmpty(str1);
//        System.out.println(str1);

        String str2 = null;
        String str3 = "";
        String str4 = " ";
        System.out.println(StringUtils.isEmpty(str2)); // true
        System.out.println(StringUtils.isBlank(str2)); // true
        System.out.println(StringUtils.isEmpty(str3)); // true
        System.out.println(StringUtils.isBlank(str3)); // true
        System.out.println(StringUtils.isEmpty(str4)); // false
        System.out.println(StringUtils.isBlank(str4)); // true
    }

    @Test
    public void test1() {
        Lion lion = new Lion();
    }

    @Test
    public void test2() {
        DecimalFormat df = new DecimalFormat("0000");
        for(int i=1; i<=225; i++) {
            System.out.println("<img src=\"images/" + df.format(i) + ".png\" />");
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
