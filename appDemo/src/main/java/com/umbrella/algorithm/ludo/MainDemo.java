package com.umbrella.algorithm.ludo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by 大洲 on 15-7-4.
 * 骰子问题
 * 两个人玩掷骰子游戏，规则是：
 * 使用三个骰子，掷出的结果，从大到小依次是，豹子（三个点数一样），顺子（三个点数连续），对子（有两个点数一样），普通（非以上类型），同类型比总点数
 * 他们玩了N次，请模拟这些操作，并算出各自的胜率（各自胜了多少次）
 */
public class MainDemo {

    private static final Logger log = LoggerFactory.getLogger(MainDemo.class);

    private static final int PLAY_TIMES = 100; // 玩的次数

    public static void main(String[] args) {
        int[] result = new int[3];
        for(int i=0; i<PLAY_TIMES; i++) {
            int k = compare(dicing(), dicing());
            if(k>0) {
                result[0]++;
            } else if(k==0) {
                result[1]++;
            } else {
                result[2]++;
            }
        }
        log.info(String.format("A 胜 %s 局， 平 %s 局， 负 %s 次", result[0], result[1], result[2]));
    }

    /**
     * 掷出三个点数
     * @return
     */
    static int[] dicing() {
        int[] res = new int[3];
        Random r1 = new Random();
        res[0] = r1.nextInt(6) + 1;
        res[1] = r1.nextInt(6) + 1;
        res[2] = r1.nextInt(6) + 1;
        return res;
    }

    /**
     * 比较骰子结果大小
     * @param arr1
     * @param arr2
     * @return 1 arr1>arr2, 0 arr1=arr2, -1 arr1<arr2
     */
    static int compare(int[] arr1, int[] arr2) {
        if(arr1!=null && arr2!=null && arr1.length==3 && arr2.length==3) {
            int typeComp = LudoType.compare(getLoduType(arr1), getLoduType(arr2));
            // 类型相同
            if(typeComp == 0) {
                int count1 = arr1[0] + arr1[1] + arr1[2];
                int count2 = arr2[0] + arr2[1] + arr2[2];
                return Integer.compare(count1, count2);
            } else {
                return typeComp;
            }
        }
        throw new RuntimeException("paramter error");
    }

    /**
     * 获取骰子结果的类型
     * @param arr
     * @return
     */
    static LudoType getLoduType(int[] arr) {
        if(arr!=null && arr.length==3) {
            Set<Integer> set1 = new HashSet<>();
            set1.add(arr[0]);
            set1.add(arr[1]);
            set1.add(arr[2]);
            if(set1.size()==1) {
                return LudoType.AAA;
            } else if(set1.size()==2) {
                return LudoType.AAB;
            } else {
                if(isJunko(arr)) {
                    return LudoType.ABC;
                } else {
                    return LudoType.CAD;
                }
            }
        }
        return null;
    }

    /**
     * 判断是不是顺子
     * @param arr 已经按从小到大排好的数组
     * @return true 是，false 否
     */
    public static boolean isJunko(int[] arr) {
        if(arr!=null && arr.length>0) {
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            for(int i=0; i<arr1.length; i++) {
                if(i<(arr1.length-2) && (arr[i+1] - arr[i])!=1) {
                    return false;
                }
            }
            return true;
        }
        throw new RuntimeException("parameter error!");
    }

}
