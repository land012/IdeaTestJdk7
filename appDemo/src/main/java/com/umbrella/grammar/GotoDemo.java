package com.umbrella.grammar;

/**
 * Created by 大洲 on 15-1-5.
 */
public class GotoDemo {
    public static void main(String[] args) {
        next: // 标签只能在 循环的前面
        for(int i=0; i<10; i++) {
            if(i%3==0) continue next;
            System.out.println(i);
        }
    }
}
