package com.umbrella.demo.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 大洲 on 15-3-4.
 * 四级地址
 */
public class FourLevelAddr {
    @Test
    public void test1() {
        // 县/区
        List<String> l_1_1_1 = new ArrayList<String>();
        l_1_1_1.add("1.1.1.1");
        l_1_1_1.add("1.1.1.2");
        l_1_1_1.add("1.1.1.3");
        Map<String, List<String>> m_1_1_1 = new HashMap<String, List<String>>();
        m_1_1_1.put("1.1.1", l_1_1_1);

        List<String> l_1_1_2 = new ArrayList<String>();
        l_1_1_2.add("1.1.2.1");
        l_1_1_2.add("1.1.2.2");
        l_1_1_2.add("1.1.2.3");
        Map<String, List<String>> m_1_1_2 = new HashMap<String, List<String>>();
        m_1_1_2.put("1.1.2", l_1_1_2);

        // 市
        List<String> l_1_1 = new ArrayList<String>();
        l_1_1.add("1.1.1");
        l_1_1.add("1.1.2");
        l_1_1.add("1.1.3");
        Map<String, List<String>> m_1_1 = new HashMap<String, List<String>>();
        m_1_1.put("1.1", l_1_1);

        // 省
        List<String> l_1 = new ArrayList<String>();
        l_1.add("1.1");
        l_1.add("1.2");
        Map<String, List<String>> m_1 = new HashMap<String, List<String>>();
        m_1.put("1", l_1);
    }
}
