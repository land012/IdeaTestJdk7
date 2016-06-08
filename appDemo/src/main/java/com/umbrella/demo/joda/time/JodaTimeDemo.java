package com.umbrella.demo.joda.time;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 大洲 on 15-1-14.
 */
public class JodaTimeDemo {

    /**
     * 入门
     * 创建 DateTime
     */
    @Test
    public void test0() {
        // 创建方式 1
        Calendar cal1 = Calendar.getInstance();
        Date date1 = cal1.getTime();
        DateTime dt1 = new DateTime(date1.getTime());
//        System.out.println(cal1);
        System.out.println(date1); // Tue Feb 16 15:57:01 CST 2016
        System.out.println(dt1); // 2016-02-16T15:57:01.855+08:00
    }

    /**
     * 创建 DateTime (2)
     */
    @Test
    public void test0_1() {
        DateTime dt1 = new DateTime(2016, 2, 16, 12, 12, 0); // 2016-02-16T12:12:00.000+08:00
        System.out.println(dt1); // 2016-02-16T12:12:00.000+08:00
    }

    /**
     * 减一个月
     */
    @Test
    public void test1() {
        DateTime dt = DateTime.now();
        System.out.println(dt); // 2016-02-16T15:54:38.783+08:00
        DateTime lastMonth = dt.minusMonths(1);
        System.out.println(lastMonth); // 2016-01-16T15:54:38.783+08:00

        DateTime dt1 = DateTime.parse("2014-12-31");
        System.out.println(dt1); // 2014-12-31T00:00:00.000+08:00
        DateTime lastMonth1 = dt1.minusMonths(1);
        System.out.println(lastMonth1); // 2014-11-30T00:00:00.000+08:00

        DateTime dt2 = DateTime.parse("2014-3-1");
        System.out.println(dt2); // 2014-03-01T00:00:00.000+08:00
        DateTime lastMonth2 = dt2.minusMonths(1);
        System.out.println(lastMonth2); // 2014-02-01T00:00:00.000+08:00
    }

    /**
     * 计算间隔时长
     */
    @Test
    public void test2() {
        Instant start = new Instant();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Interval(start, new Instant()).toDuration().getMillis());
    }

    /**
     * DateTimeFormatter
     */
    @Test
    public void test4() {
        DateTime dt1 = new DateTime(2016, 2, 16, 12, 12, 0); // 2016-02-16T12:12:00.000+08:00
        System.out.println(dt1); // 2016-02-16T12:12:00.000+08:00

        DateTimeFormatter dtfer1 = DateTimeFormat.shortDate();
        System.out.println(dtfer1.print(dt1)); // 16-2-16
    }

    /**
     * LocaleDateTime
     */
    @Test
    public void test5() {
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
     * Days
     */
    @Test
    public void test3_0() {
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
    public void test3_0_2() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dt1 = DateTime.parse("2015-11-09", dtfer);
        DateTime dt2 = DateTime.parse("2016-06-05", dtfer);

        Days days1 = Days.daysBetween(dt1, dt2);
        System.out.println(days1); // P2D
        System.out.println(days1.getDays()); // 2
        System.out.println(days1.getPeriodType().getName()); // Days
    }

    /**
     * Period
     */
    @Test
    public void test3_1() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2016-02-13 12:12:00", dtfer);
        DateTime dt2 = DateTime.parse("2016-02-16 12:00:00", dtfer);

        Period period1 = new Period(dt1, dt2);
        System.out.println(period1.getDays());    // 2
        System.out.println(period1.getHours());   // 23
        System.out.println(period1.getMinutes()); // 48
    }

    /**
     * Duration
     */
    @Test
    public void test3_2() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2016-02-13 12:12:00", dtfer);
        DateTime dt2 = DateTime.parse("2016-02-16 12:00:00", dtfer);

        Duration duration1 = new Duration(dt1, dt2);
        System.out.println(duration1.getStandardDays()); // 2
    }

    /**
     * 日期 的日间隔
     * 这种算法，必须是在同一年同一个月
     */
    @Test
    public void test6() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2016-02-13 12:12:00", dtfer);
        System.out.println(dt1); // 2016-02-13T12:12:00.000+08:00
        DateTime dt2 = DateTime.parse("2016-03-16 12:00:00", dtfer);

        System.out.println(dt2.dayOfMonth().get()-dt1.dayOfMonth().get()); // 3
    }
}
