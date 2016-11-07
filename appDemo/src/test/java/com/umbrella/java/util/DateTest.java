package com.umbrella.java.util;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 大洲 on 14-12-18.
 */
public class DateTest {
    @Test
    public void test1() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2014, 11, 31, 15, 20, 59); // 2014-12-31 15:20:59
        Date d1 = c1.getTime();
        Calendar c2 = Calendar.getInstance();
        c2.set(2014, 11, 31, 15, 20, 59);
        Date d2 = c2.getTime();
        System.out.println(d1);
        System.out.println(d1==d2);        // false
        System.out.println(d1.equals(d2)); // false
        System.out.println(c1==c2);        // false
        System.out.println(c1.equals(c2)); // false
    }

    @Test
    public void test2() {
        
    }
}
