package com.umbrella.demo.db;

import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by xudazhou on 2017/3/28.
 */
public class DateSqlDemo {

    /**
     * java.sql.Date
     */
    @Test
    public void test1() {
        java.util.Date d1 = new java.util.Date();
        Date d2 = new Date(d1.getTime());
        System.out.println(d2); // 2017-03-28
    }

    /**
     *
     */
    @Test
    public void test2() {
        java.util.Date d1 = new java.util.Date();
        Timestamp t1 = new Timestamp(d1.getTime());
        System.out.println(t1); // 2017-03-28 11:32:46.488
    }
}
