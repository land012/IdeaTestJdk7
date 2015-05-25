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
    public static void main(String[] args) {
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

        cal1.add(Calendar.DAY_OF_MONTH, -1); // 前一天
        System.out.println(cal1.get(Calendar.YEAR));
        System.out.println(cal1.get(Calendar.MONTH));
        System.out.println(cal1.get(Calendar.DAY_OF_MONTH));

        System.out.println(cal1.getTime());
        cal1.set(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), cal1.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        System.out.println(cal1.getTime());
    }

    @Test
    public void test2() throws ParseException {
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2015-01-12");
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        System.out.println(cal1.getTime());
    }
}
