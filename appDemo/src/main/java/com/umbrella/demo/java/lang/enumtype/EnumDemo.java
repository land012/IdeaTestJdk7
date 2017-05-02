package com.umbrella.demo.java.lang.enumtype;

import org.junit.Test;

/**
 * Created by 大洲 on 14-11-19.
 */
public class EnumDemo {

    /**
     * values()
     */
    @Test
    public void test1() {
        StatusEnum[] statuses = StatusEnum.values();
        for(StatusEnum s : statuses) {
            System.out.println(s.getValue());
            System.out.println(s.name());
        }
    }

    /**
     * valueOf()
     */
    @Test
    public void test2() {
        System.out.println(StatusEnum.SUCCESS.name()); // SUCCESS
        System.out.println(StatusEnum.SUCCESS.ordinal()); // 0
        System.out.println(StatusEnum.SUCCESS); // SUCCESS
        StatusEnum statusEnum1 = StatusEnum.valueOf("SUCCESS");
        System.out.println(statusEnum1); // SUCCESS
    }

    /**
     * toString()
     */
    @Test
    public void test3() {
        System.out.println(StatusEnum.SUCCESS.toString()); // SUCCESS
    }
}
