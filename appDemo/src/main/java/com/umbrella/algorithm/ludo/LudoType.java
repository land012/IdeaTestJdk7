package com.umbrella.algorithm.ludo;

/**
 * Created by 大洲 on 15-7-5.
 * 掷出骰子的类型
 */
public enum LudoType {
    AAA, // 豹子
    ABC, // 顺子
    AAB, // 对子
    CAD  // 普通
    ;

    /**
     * 比较大小
     * @param type1
     * @param type2
     * @return 1 type1>type2， 0 type1=type2， -1 type1<type2
     */
    public static int compare(LudoType type1, LudoType type2) {
        if(type1.ordinal() > type2.ordinal()) {
            return -1;
        } else if(type1.ordinal() == type2.ordinal()) {
            return 0;
        } else {
            return 1;
        }
    }
}
