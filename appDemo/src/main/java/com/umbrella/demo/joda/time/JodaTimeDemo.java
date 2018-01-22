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

        System.out.println(dt1.millisOfSecond().get()); // 263
        System.out.println(dt1.getMillis()); // 1487677399263
        System.out.println(dt1.toInstant().getMillis()); // 1487677399263
    }

    /**
     * 创建 DateTime (2)
     */
    @Test
    public void test0_1() {
        DateTime dt1 = new DateTime(2016, 2, 16, 12, 12, 0); // 2016-02-16T12:12:00.000+08:00
        System.out.println(dt1); // 2016-02-16T12:12:00.000+08:00

        LocalDate date1 = dt1.toLocalDate();
        System.out.println(date1); // 2016-02-16

        LocalDate date2 = date1.minusDays(6);
        System.out.println(date2); // 2016-02-10

        date1 = date1.minusMonths(2);
        System.out.println(date1); // 2015-12-16     w
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
        System.out.println(dt2); // 2014-03-01T00:00:00.000+08:00
        System.out.println(dt2.isBefore(lastMonth2)); // false
    }

    /**
     * 计算间隔时长 Interval
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

}
