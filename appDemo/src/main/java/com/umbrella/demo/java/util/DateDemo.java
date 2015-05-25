package com.umbrella.demo.java.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 大洲 on 14-12-15.
 */
public class DateDemo {

    public static void main(String[] args) {
        try {
            Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-12-14");
            Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-12-15");
            System.out.println(d2.after(d1)); // true
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        Date curdate1 = new Date();
        System.out.println(curdate1.toString());    // Sat Nov 22 10:52:49 CST 2014
        System.out.println(curdate1.toGMTString()); // 22 Nov 2014 02:52:49 GMT(0时区的时间，比北京时间慢8小时)
    }

    /**
     * 日期比较
     * @throws ParseException
     */
    @Test
    public void test2() throws ParseException {
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01");
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-05-01");
        Date d3 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-05-01");
        System.out.println(d1.before(d2)); // true
        System.out.println(d2.before(d3)); // false
        System.out.println(d1.compareTo(d2)); // -1
        System.out.println(d2.compareTo(d3)); // 0
    }

    /**
     * 日期可以用 equals 相比
     * @throws ParseException
     */
    @Test
    public void test22() throws ParseException {
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01");
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01");
        System.out.println(d1.equals(d2)); // true
    }


    /**
     *
     * @throws ParseException
     */
    @Test
    public void test3() throws ParseException {
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-01-01");
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-05-01");
        Date d3 = new SimpleDateFormat("yyyy-MM-dd").parse("2014-05-01");
        Map<Date, String> map1 = new HashMap<Date, String>();
        map1.put(d2, "a");
        System.out.println(map1.get(d3)); // a
        System.out.println(map1.get(d1)); // null
    }

    /**
     * 1970-1-1 8:00:00
     */
    @Test
    public void test4() {
        Date d1 = new Date(0);
        System.out.println(d1);
    }
}
