package com.umbrella.demo.java.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 大洲 on 14-11-11.
 */
public class CalendarDemo {

    @Test
    public void test0() {
        Calendar cal1 = Calendar.getInstance();
        System.out.println(cal1);
        System.out.println(cal1.getTimeZone());
        System.out.println(cal1.getTime());
        int zoneOffset = cal1.get(Calendar.ZONE_OFFSET);
        int dstOffset = cal1.get(Calendar.DST_OFFSET);
        cal1.add(Calendar.MILLISECOND, -(zoneOffset+dstOffset));
        System.out.println(cal1.getTime()); // GMT时间
    }

    @Test
    public void test1() {
        Calendar cal1 = Calendar.getInstance();
        System.out.println(cal1.get(Calendar.YEAR));
        System.out.println(cal1.get(Calendar.MONTH)); // 月比实际数值小1
        System.out.println(cal1.get(Calendar.DAY_OF_MONTH));

        // 减一天
        cal1.add(Calendar.DAY_OF_MONTH, -1); // 前一天
        System.out.println(cal1.get(Calendar.YEAR));
        System.out.println(cal1.get(Calendar.MONTH));
        System.out.println(cal1.get(Calendar.DAY_OF_MONTH));

        // 将时间指定为 0
        System.out.println(cal1.getTime());
        cal1.set(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), cal1.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        System.out.println(cal1.getTime());
    }

    /**
     *
     * @throws ParseException
     */
    @Test
    public void test2() throws ParseException {
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2017-04-29");
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        cal1.add(Calendar.DAY_OF_MONTH, 280);
        System.out.println(cal1.getTime());
    }

    /**
     * 48小时前的时间
     */
    @Test
    public void test3() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DAY_OF_MONTH, -2);
        System.out.println(now.getTime());
    }

    /**
     * clone
     */
    @Test
    public void test4() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = (Calendar)cal1.clone();
        Calendar cal3 = cal1;
        System.out.println(cal1.hashCode());
        System.out.println(cal2.hashCode());
        System.out.println(cal1==cal2); // false
        System.out.println(cal1==cal3); // true
    }

    /**
     * 打印星期一
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse("2015-09-07");
        cal.setTime(d1);

        Date d2 = sdf.parse("2016-06-06");
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);
        for(int i=46; cal.before(cal2); cal.add(Calendar.DAY_OF_MONTH, 1)) {
            if(cal.get(Calendar.DAY_OF_WEEK)==2) {
                System.out.println(sdf.format(cal.getTime()) + "-" + i);
                i++;
            }
        }
    }

    /**
     * 日期比较
     */
    @Test
    public void test6_compare() {
        Date d1 = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d1);
        cal2.add(Calendar.MINUTE, 50);
        System.out.println(cal1.compareTo(cal2)); // -1

        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        System.out.println(sdf.format(cal2.getTime())); // 2024
        // 循环 5 次
        while (cal1.compareTo(cal2) < 0) {
            System.out.println(sdf.format(cal1.getTime()));
            cal1.add(Calendar.MINUTE, 10);
        }

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        System.out.println(sdf2.format(cal1.getTime())); // 20170303203543794
        System.out.println(sdf2.format(cal2.getTime())); // 20170303202543798
    }

    @Test
    public void test6_compare_2() {
        Date d1 = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d1);

        System.out.println(cal1.before(cal2)); // false

        cal2.add(Calendar.MINUTE, 1);

        System.out.println(cal1.before(cal2)); // true
    }
}
