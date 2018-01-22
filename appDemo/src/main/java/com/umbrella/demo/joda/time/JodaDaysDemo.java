package com.umbrella.demo.joda.time;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

/**
 * create by xudazhou 2017/12/29
 */
public class JodaDaysDemo {
    /**
     * Days
     * 如果日期中包含 HH:mm:dd，那么不足3天时，显示为 2 天
     */
    @Test
    public void test3_0_days_time() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2016-02-13 12:12:00", dtfer);
        System.out.println(dt1); // 2016-02-13T12:12:00.000+08:00
        DateTime dt2 = DateTime.parse("2016-02-16 12:00:00", dtfer);

        Days days1 = Days.daysBetween(dt1, dt2);
        System.out.println(days1); // P2D
        System.out.println(days1.getDays()); // 2
        System.out.println(days1.getPeriodType().getName()); // Days
    }

    /**
     * Days
     */
    @Test
    public void test3_0_2_days() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dt1 = DateTime.parse("2015-11-09", dtfer);
        DateTime dt2 = DateTime.parse("2015-12-09", dtfer);

        Days days1 = Days.daysBetween(dt1, dt2);
        System.out.println(days1); // P30D
        System.out.println(days1.getDays()); // 30
        System.out.println(days1.getPeriodType().getName()); // Days
    }
}
