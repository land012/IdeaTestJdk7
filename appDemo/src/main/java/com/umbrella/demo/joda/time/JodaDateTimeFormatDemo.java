package com.umbrella.demo.joda.time;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

/**
 * create by xudazhou 2017/12/29
 */
public class JodaDateTimeFormatDemo {
    /**
     * DateTimeFormatter
     * toString()
     */
    @Test
    public void test4_datetimeformatter() {
        DateTime dt1 = new DateTime(2016, 2, 16, 12, 12, 0); // 2016-02-16T12:12:00.000+08:00
        System.out.println(dt1); // 2016-02-16T12:12:00.000+08:00
        System.out.println(dt1.toString("yyyy-MM-dd HH:mm:ss")); // 2016-02-16 12:12:00
        System.out.println(dt1.toString("HH:mm:ss")); // 12:12:00

        DateTimeFormatter dtfer1 = DateTimeFormat.shortDate();
        System.out.println(dtfer1.print(dt1)); // 16-2-16
    }

    /**
     * 短 pattern 解析长日期时会异常
     * java.lang.IllegalArgumentException: Invalid format: "2017-12-18 11:20:01" is malformed at ":01"
     */
    @Test
    public void test5_parse() {
        String str1 = "2017-12-18 11:20:01";
        DateTime dt1 = DateTime.parse(str1, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm"));
        System.out.println(dt1);
    }

    /**
     * 长 pattern 解析短日期时会异常
     * java.lang.IllegalArgumentException: Invalid format: "2017-12-18 11:20" is too short
     */
    @Test
    public void test6_parse() {
        String str1 = "2017-12-18 11:20";
        DateTime dt1 = DateTime.parse(str1, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(dt1);
    }
}
