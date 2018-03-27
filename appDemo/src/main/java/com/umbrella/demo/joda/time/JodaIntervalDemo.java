package com.umbrella.demo.joda.time;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

/**
 * create by xudazhou 2018/3/22
 */
public class JodaIntervalDemo {

    /**
     * 一段时间
     */
    @Test
    public void test1() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2018-03-21 12:50:00", dtfer);
        DateTime dt2 = DateTime.parse("2018-03-21 13:00:00", dtfer);
        System.out.println(dt1);

        Interval interval1 = new Interval(dt1, dt2);
        System.out.println(interval1.containsNow()); // false
        System.out.println(interval1.isBeforeNow()); // true
    }
}
