package com.umbrella.demo.collections;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudazhou on 2017/2/8.
 */
public class MapDemo {
    @Test
    public void test1() {
        Map<String, Object> map1 = new HashMap<>();
        String str1 = (String) map1.get("k1");
        System.out.println(str1); // null
    }

    @Test
    public void test2() {
        Map<String, Double> m1 = new HashMap<>();
        m1.put("a", 1.3);
        m1.put("哈哈", 0.44);
        System.out.println(m1.containsKey("a")); // true
        System.out.println(m1.containsKey("哈哈")); // true
        System.out.println(null != m1.get("哈哈")); // true
    }
}
