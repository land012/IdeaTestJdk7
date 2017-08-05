package com.umbrella.demo.java.time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by xudazhou on 2017/7/19.
 */
public class LocalDateTimeDemo {

    @Test
    public void test1() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt); // 2017-07-19T17:36:15.665
        System.out.println(ldt.format(DateTimeFormatter.BASIC_ISO_DATE)); // 20170719
        System.out.println(ldt.format(DateTimeFormatter.ISO_DATE)); // 2017-07-19
        System.out.println(ldt.format(DateTimeFormatter.ISO_LOCAL_DATE)); // 2017-07-19
        System.out.println(ldt.format(DateTimeFormatter.ISO_DATE_TIME)); // 2017-07-19T17:36:15.665
        System.out.println(ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // 2017-07-19T17:36:15.665

        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: OffsetSeconds
//        System.out.println(ldt.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)); // 2017-07-19T17:36:15.665

        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: InstantSeconds
//        System.out.println(ldt.format(DateTimeFormatter.ISO_INSTANT));
    }

    @Test
    public void test2() {
        String str1 = "2017-07-12T15:12";
        LocalDateTime ldt1 = LocalDateTime.parse(str1, DateTimeFormatter.ISO_ZONED_DATE_TIME);
        System.out.println(ldt1); // 2017-07-12T15:12
        System.out.println(ldt1.atZone(ZoneId.of("Japan"))); // 2017-07-12T15:12+09:00[Japan]
        System.out.println(ldt1.atZone(ZoneId.of("Asia/Shanghai"))); // 2017-07-12T15:12+08:00[Asia/Shanghai]

    }

    @Test
    public void test3() {
        Date d1 = new Date();
        LocalDateTime ldt1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(ldt1.format(DateTimeFormatter.ISO_DATE_TIME)); // 2017-07-19T19:13:18.372

        // java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: OffsetSeconds
//        System.out.println(ldt1.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
    }
}
