package com.umbrella.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 大洲 on 15-7-3.
 * 长度为100的数组，里面存的是 1-10 的整数
 * 算出出现次数最多和最少的数
 * 效率越高越好
 */
public class NumberCounting {

    private static final Logger log = LoggerFactory.getLogger(NumberCounting.class);

    public static void main(String[] args) {
        NumberCounting numberCounting = new NumberCounting();
        Integer[] arr1 = numberCounting.initArr();
        Map<Integer, Integer> map1 = new HashMap<>();
        for(int i=0; i<arr1.length; i++) {
            int k1 = arr1[i];
            if(map1.containsKey(k1)) {
                int v1 = map1.get(k1);
                v1++;
                map1.put(k1, v1);
            } else {
                map1.put(k1, 1);
            }
        }
        System.out.println(map1);
    }

    /**
     * 初始化数组
     */
    private Integer[] initArr() {
        Integer[] arr = new Integer[100];
        for(int i=0; i<arr.length; i++) {
            int k = (int)(Math.random() * 10);
            arr[i] = k + 1;
            System.out.print(arr[i] + ",");
        }
        log.info("end");
        return arr;
    }
}
