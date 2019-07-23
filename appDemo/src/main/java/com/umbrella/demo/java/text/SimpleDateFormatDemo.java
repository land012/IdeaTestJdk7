package com.umbrella.demo.java.text;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 大洲 on 14-11-13.
 */
public class SimpleDateFormatDemo {

    @Test
    public void test1() {
        Date d1 = new Date();

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX", Locale.CHINA);
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX", Locale.US);
        SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssXX", Locale.CHINA);
        SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssXX", Locale.US);
        SimpleDateFormat sdf7 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.CHINA);
        SimpleDateFormat sdf8 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.US);
        SimpleDateFormat sdf9 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE MMM a G", Locale.CHINA);
        SimpleDateFormat sdf10 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE MMM a G", Locale.US);
        System.out.println(sdf1.format(d1)); // 2015-05-07 19:30:39
        System.out.println(sdf2.format(d1)); // 2015-05-07 19:30:39
        System.out.println(sdf3.format(d1)); // 2015-05-08 10:29:06+08
        System.out.println(sdf4.format(d1)); // 2015-05-08 10:29:06+08
        System.out.println(sdf5.format(d1)); // 2015-05-08 10:29:06+0800
        System.out.println(sdf6.format(d1)); // 2015-05-08 10:29:06+0800
        System.out.println(sdf7.format(d1)); // 2015-05-08 10:31:17 CST
        System.out.println(sdf8.format(d1)); // 2015-05-08 10:31:17 CST
        System.out.println(sdf9.format(d1)); // 2015-05-08 10:35:35 星期五 五月 上午 公元
        System.out.println(sdf10.format(d1)); // 2015-05-08 10:35:35 Fri May AM AD
    }

    /**
     * getDateTimeInstance()
     */
    @Test
    public void test2() {
        SimpleDateFormat df1 = (SimpleDateFormat)DateFormat.getDateTimeInstance();
        System.out.println(df1.toString());
        System.out.println(df1.toPattern()); // yyyy-M-d H:mm:ss
        System.out.println(df1.format(new Date())); // 2016-1-12 16:21:08
    }

    /**
     * 异常
     * 长 pattern 解析短日期会抛异常
     * java.text.ParseException: Unparseable date: "2015-12-12"
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        String dateStr = "2015-12-12";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.toPattern()); // yyyy-MM-dd HH:mm:ss
        Date d1 = sdf.parse(dateStr);
        System.out.println(d1);
    }

    /**
     * 不会抛异常
     * 短 pattern 解析长日期不会异常，会忽略掉后面的时间
     * @throws Exception
     */
    @Test
    public void test4_2() throws Exception {
        String dateStr = "2015-12-12 11:10:00";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf2.toPattern()); // yyyy-MM-dd
        Date d1 = sdf2.parse(dateStr); // Sat Dec 12 00:00:00 CST 2015
        System.out.println(d1);
    }

    /**
     * 不会异常
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        String dateStr = "2015-12-12 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.toPattern()); // yyyy-MM-dd HH:mm:ss
        Date d1 = sdf.parse(dateStr); // Sat Dec 12 00:00:00 CST 2015
        System.out.println(d1);
    }

    /**
     * 不会异常
     */
    @Test
    public void test5() {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date d1 = sdf1.parse("2014-12-12 12:12:12.22");
            System.out.println(d1); // Fri Dec 12 12:12:12 CST 2014
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 不会抛异常
     * @throws Exception
     *
     */
    @Test
    public void test6() throws Exception {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        Date d1 = sdf1.parse("2016-01");
        System.out.println(d1); // Fri Jan 01 00:00:00 CST 2016
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1Str = sdf2.format(d1);
        System.out.println(d1Str); // 2016-01-01 00:00:00
    }

    @Test
    public void test7_time() {
        Date d1 = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
        System.out.println(sdf1.format(d1)); // 19:02
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:00");
        System.out.println(sdf2.format(d1)); // 19:00

        System.out.println(new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(new Date()));
    }
}

