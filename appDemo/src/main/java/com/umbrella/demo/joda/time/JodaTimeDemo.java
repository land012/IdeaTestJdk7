package com.umbrella.demo.joda.time;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public void test0() throws Exception {
        // 创建方式 1
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date1 = sdf.parse("2017-02-21 19:43:19.263");
        DateTime dt1 = new DateTime(date1.getTime());
        DateTime dt2 = new DateTime(date1);
        System.out.println(date1); // Tue Feb 21 19:43:19 CST 2017
        System.out.println(dt1); // 2017-02-21T19:43:19.263+08:00
        System.out.println(dt2); //2017-02-21T19:43:19.263+08:00
        System.out.println(dt1.getChronology()); // ISOChronology[Asia/Shanghai]
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
    public void test1_minusMonth() {
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
    public void test2_instant() {
        Instant start = new Instant();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(start); // 2017-02-21T11:19:21.935Z
        System.out.println(new Interval(start, new Instant()).toDuration().getMillis()); // 3097
    }

    /**
     * DateTimeFormatter
     */
    @Test
    public void test4_datetimeformatter() {
        DateTime dt1 = new DateTime(2016, 2, 16, 12, 12, 0); // 2016-02-16T12:12:00.000+08:00
        System.out.println(dt1); // 2016-02-16T12:12:00.000+08:00
        System.out.println(dt1.toString("yyyy-MM-dd HH:mm:ss")); // 2016-02-16 12:12:00

        DateTimeFormatter dtfer1 = DateTimeFormat.shortDate();
        System.out.println(dtfer1.print(dt1)); // 16-2-16
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
    }

    /**
     * 日期 的日间隔
     * 这种算法，必须是在同一年同一个月
     */
    @Test
    public void test6_days_interval() {
        DateTimeFormatter dtfer = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt1 = DateTime.parse("2016-02-13 12:12:00", dtfer);
        System.out.println(dt1); // 2016-02-13T12:12:00.000+08:00
        DateTime dt2 = DateTime.parse("2016-03-16 12:00:00", dtfer);

        System.out.println(dt2.dayOfMonth().get()-dt1.dayOfMonth().get()); // 3
    }

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
}
