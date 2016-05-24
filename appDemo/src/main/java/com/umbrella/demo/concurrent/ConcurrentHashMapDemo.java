package com.umbrella.demo.concurrent;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xudazhou on 2016/5/21.
 */
public class ConcurrentHashMapDemo {
    @Test
    public void test1() {
        ConcurrentHashMap<String, String> m1 = new ConcurrentHashMap<>();
        m1.put("k1", "v1");
        System.out.println(m1.size());
    }
}
