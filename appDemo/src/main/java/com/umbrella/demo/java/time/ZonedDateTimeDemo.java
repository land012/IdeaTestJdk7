package com.umbrella.demo.java.time;

import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by xudazhou on 2017/7/19.
 */
public class ZonedDateTimeDemo {

    @Test
    public void test1() {
        ZonedDateTime zdt1 = ZonedDateTime.now();
        System.out.println(zdt1.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)); // 2017-07-19T19:16:17.167+08:00[Asia/Shanghai]
    }
}
