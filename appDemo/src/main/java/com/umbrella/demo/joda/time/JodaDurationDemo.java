package com.umbrella.demo.joda.time;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

/**
 * create by xudazhou 2018/3/22
 */
public class JodaDurationDemo {

    /**
     * Duration
     */
    @Test
    public void test3_2_duration() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2016-02-13 12:12:00", dtfer);
        DateTime dt2 = DateTime.parse("2016-02-16 12:00:00", dtfer);

        Duration duration1 = new Duration(dt1, dt2);
        System.out.println(duration1.getStandardDays()); // 2
        System.out.println(duration1.getStandardHours()); // 71
        System.out.println(duration1.getStandardMinutes()); // 4308
    }

    /**
     * Duration
     */
    @Test
    public void test2_duration() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2016-02-13 12:12:00", dtfer);
        DateTime dt2 = DateTime.parse("2016-02-16 12:00:00", dtfer);

        Duration duration1 = new Duration(dt2, dt1);
        System.out.println(duration1.getStandardDays()); // -2
        System.out.println(duration1.getStandardHours()); // -71
        System.out.println(duration1.getStandardMinutes()); // -4308
    }
}
