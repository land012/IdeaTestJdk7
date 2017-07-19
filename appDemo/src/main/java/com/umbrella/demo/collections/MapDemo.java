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
}
