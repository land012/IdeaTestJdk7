package com.umbrella.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 大洲 on 15-7-4.
 * 约瑟夫环
 * M 个人围成一圈，每个人都有自己的编号（1-M），指定一个编号从1开始查数，查到 N 就把该人踢出圈子，继续从1开始查数，
 * 重复该操作，直到只剩下最后一个人为上，问最后一个的编号是多少
 */
public class Josephus {

    private static final int MEMBERS = 7; // 总人数
    private static final int COUNT = 5; /// 数的数N

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<MEMBERS; i++) {
            list.add(i+1);
        }
        System.out.println(list);

        int j = 1; // 从1开始数
        int index = 0;
        while (list.size()>1) {
            if(j==COUNT) {
                System.out.println("remove=" + list.get(index));
                list.remove(index);

                if(index>=list.size()) index=0;
                j=1;
                continue;
            }

            index++;
            if(index>=list.size()) index=0;
            j++;
        }
        System.out.println("list.size()=" + list.size());
        System.out.println("last=" + list.get(0));
    }
}
