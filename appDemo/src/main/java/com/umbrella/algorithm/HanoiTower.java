package com.umbrella.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 大洲 on 15-7-4.
 * 汉诺塔
 * 有 A,B,C 三根柱子，A 上有 N 个大小不同的铁盘，从小到大叠放在 A 上，
 * 现希望借助 B，将铁盘移到 C 上，只能一个一个移，且小的必须放到大的上面，请输出移动步骤
 */
public class HanoiTower {

    private static final int NUM_OF_DISH = 3;

    public static void main(String[] args) {
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        List<Integer> listC = new ArrayList<>();
        for(int i=NUM_OF_DISH; i>=1; i--) {
            listA.add(i);
        }
        move(NUM_OF_DISH, "A", "B", "C", listA, listB, listC);
    }

    /**
     * 递归
     * @param n n个盘子
     * @param a
     * @param b
     * @param c
     * @param listA List[0] 是最下面的盘子（也就是最大的盘子），List[size-1] 是最上面的盘子（也就是最小的盘子）
     * @param listB
     * @param listC
     */
    public static void move(int n, String a, String b, String c, List<Integer> listA, List<Integer> listB, List<Integer> listC) {
        // 如果只还有一个盘子，直接从 A 移到 C 上
        if(n==1) {
            System.out.println(String.format("move %s from %s to %s last", n, a, c));
            listA.remove(listA.size()-1); // 移走最上面的盘子
            listC.add(1); // C 柱子加盘子1
            System.out.println(String.format("%s=%s, %s=%s, %s=%s", a, listA, b, listB, c, listC));
            return;
        }
        // 先把 n-1 个盘子，借助C，移到 B 上
        move(n-1, a, c, b, listA, listC, listB);
        // 把第n个盘从A移到C上
        System.out.println(String.format("move %s from %s to %s", n, a, c));
        listA.remove(listA.size()-1);
        listC.add(n); // C柱子 加 盘子n
        System.out.println(String.format("%s=%s, %s=%s, %s=%s", a, listA, b, listB, c, listC));
        // 把 B 上的 n-1个盘子，借助A，移动 C 上
        move(n - 1, b, a, c, listB, listA, listC);
    }
}
