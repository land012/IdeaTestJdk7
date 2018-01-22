package com.umbrella.demo.joda.time;

import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

/**
 * create by xudazhou 2017/12/29
 */
public class JodaPeriodDemo {
    /**
     * Period
     * 标准 period，日期相差不足一周
     */
    @Test
    public void test3_1_period() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2016-02-13 12:12:00", dtfer);
        DateTime dt2 = DateTime.parse("2016-02-16 12:00:00", dtfer);

        Period period1 = new Period(dt1, dt2);
        System.out.println(period1.getDays()); // 2
        System.out.println(period1.get(DurationFieldType.days())); // 2
        System.out.println(period1.toStandardDays()); // P2D
        System.out.println(period1.getHours()); // 23
        System.out.println(period1.getMinutes()); // 48
        System.out.println(period1.getSeconds()); // 0
    }

    /**
     * Period
     * 标准 period，日期相差超过一周
     */
    @Test
    public void test3_2_period() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2017-02-04 00:00:00", dtfer);
        DateTime dt2 = DateTime.parse("2017-02-15 00:00:00", dtfer);

        Period period1 = new Period(dt1, dt2);
        System.out.println(period1.getMonths()); // 0
        System.out.println(period1.getWeeks()); // 1
        System.out.println(period1.getDays()); // 4 ????? 因为前面有一个完整的周
        System.out.println(period1.get(DurationFieldType.days())); // 4
        System.out.println(period1.toStandardDays()); // P11D
        System.out.println(period1.getHours()); // 0
    }

    /**
     * Period
     */
    @Test
    public void test3_3_period() {
        // 标准 period，日期相差正好一周
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2017-02-04 00:00:00", dtfer);
        DateTime dt2 = DateTime.parse("2017-02-11 00:00:00", dtfer);
        Period period1 = new Period(dt1, dt2);
        System.out.println(period1.getMonths()); // 0
        System.out.println(period1.getWeeks()); // 1
        System.out.println(period1.getDays()); // 0

        System.out.println("================== 1 ====================");

        // 日期相差正好一月
        DateTime dt3 = DateTime.parse("2017-02-04 00:00:00", dtfer);
        DateTime dt4 = DateTime.parse("2017-03-04 00:00:00", dtfer);
        Period period2 = new Period(dt3, dt4);
        System.out.println(period2.getMonths()); // 1
        System.out.println(period2.getWeeks()); // 0
        System.out.println(period2.getDays()); // 0

        System.out.println("================== 2 ====================");

        // 日期相差超过一月，但不到一月零一周
        DateTime dt5 = DateTime.parse("2017-02-04 00:00:00", dtfer);
        DateTime dt6 = DateTime.parse("2017-03-05 00:00:00", dtfer);
        Period period3 = new Period(dt5, dt6);
        System.out.println(period3.getMonths()); // 1
        System.out.println(period3.getWeeks()); // 0
        System.out.println(period3.getDays()); // 1
    }

    /**
     * Period
     * Day
     */
    @Test
    public void test3_4_period() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2017-02-04 00:00:00", dtfer);
        DateTime dt2 = DateTime.parse("2017-02-14 02:00:00", dtfer);

        Period period1 = new Period(dt1, dt2, PeriodType.days());
        System.out.println(period1.getMonths()); // 0
        System.out.println(period1.getWeeks()); // 0
        System.out.println(period1.getDays()); // 10
        System.out.println(period1.getHours()); // 0
    }

    /**
     * Period
     * Day
     */
    @Test
    public void test3_5_period() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2017-02-04 00:00:00", dtfer);
        DateTime dt2 = DateTime.parse("2018-03-05 02:00:00", dtfer);

        Period period1 = new Period(dt1, dt2, PeriodType.dayTime());
        System.out.println(period1.getYears()); // 0
        System.out.println(period1.getMonths()); // 0
        System.out.println(period1.getWeeks()); // 0
        System.out.println(period1.getDays()); // 394
        System.out.println(period1.getHours()); // 2
    }

    /**
     * Period
     * Day
     */
    @Test
    public void test3_6_period() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2017-02-04 00:00:00", dtfer);
        DateTime dt2 = DateTime.parse("2019-03-05 02:00:00", dtfer);

        Period period1 = new Period(dt1, dt2, PeriodType.yearMonthDayTime());
        System.out.println(period1.getYears()); // 2
        System.out.println(period1.getMonths()); // 1
        System.out.println(period1.getWeeks()); // 0
        System.out.println(period1.getDays()); // 1
        System.out.println(period1.getHours()); // 2
    }
}
