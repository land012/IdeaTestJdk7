package com.umbrella.demo.java.text;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 大洲 on 14-11-13.
 */
public class SimpleDateFormatDemo {

    @Test
    public void test1() {
        /*SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date d1 = sdf1.parse("2014-12-12 12:12:12.22");
            System.out.println(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        DateFormat df1 = DateFormat.getDateTimeInstance();
        Date d1 = new Date();
//        System.out.println(d1); // Tue Dec 02 15:37:06 CST 2014
//        System.out.println(df1.format(d1)); // 2014-12-2 15:37:06

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

    @Test
    public void test2() {
        DateFormat df1 = DateFormat.getDateTimeInstance();
        System.out.println(df1.toString());
        System.out.println(df1.format(new Date())); // 2015-5-8 10:51:41
    }
}

