package com.umbrella.demo.joda.time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.Date;

/**
 * create by xudazhou 2017/12/22
 */
public class LocalDateDemo {
    /**
     * 仅获取日期部分
     */
    @Test
    public void test7_1_localdate() {
        DateTime dt = DateTime.now();
        System.out.println(dt); // 2017-02-21T18:58:12.586+08:00

        LocalDate ld = new LocalDate();
        System.out.println(ld); // 2017-02-21
        System.out.println(ld.toDate()); // Tue Feb 21 00:00:00 CST 2017
    }

    /**
     * LocalDateTime
     */
    @Test
    public void test5_localdatetime() {
        DateTime dt1 = new DateTime(2016, 2, 16, 12, 12, 0); // 2016-02-16T12:12:00.000+08:00
        System.out.println(dt1); // 2016-02-16T12:12:00.000+08:00

        System.out.println(dt1.dayOfMonth().get()); // 16

        LocalDate ld1 = dt1.toLocalDate();
        System.out.println(ld1.getDayOfMonth()); // 16

        LocalDateTime ldt1 = dt1.toLocalDateTime();
        System.out.println(ldt1.get(DateTimeFieldType.dayOfMonth())); // 16
        System.out.println(DateTimeFieldType.dayOfMonth().getName()); // dayOfMonth
    }

    /**
     * LocalDate
     * 可以把 yyyy-MM-dd HH:mm:ss 截断为 yyyy-MM-dd
     */
    @Test
    public void test8_localdate() {
        Date d1 = new Date();
        System.out.println(d1); // Tue Feb 21 19:01:30 CST 2017

        LocalDate ld1 = new LocalDate(d1.getTime());
        System.out.println(ld1); // 2017-02-21
        System.out.println(ld1.toDate()); // Tue Feb 21 00:00:00 CST 2017
    }

    @Test
    public void test2() {
        LocalDate end = new LocalDate(2017, 12, 13);
        LocalDate begin = end.minusDays(3);
        while (!begin.isAfter(end)) {
            System.out.println(begin);
            begin = begin.plusDays(1);
        }
    }
}
