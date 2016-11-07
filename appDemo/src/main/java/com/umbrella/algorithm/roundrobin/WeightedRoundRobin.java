package com.umbrella.algorithm.roundrobin;

/**
 * 带权重的 roundrobin
 * Created by xudazhou on 2016/10/25.
 * 数值越大表示权重越高
 */
public class WeightedRoundRobin {
    private int[] servers;
    private int maxWeight; // 最大权重值
    private int cw = 0; // 比较因子
    private int number = -1; // 机器序号
    private int gcd; // 最大公约数

    public WeightedRoundRobin(int[] servers) {
        this.servers = servers;
        this.maxWeight = getMaxWeight();
        this.gcd = getBCD();
    }

    private int getMaxWeight() {
        int maxWeight = servers[0];
        for(int i=1; i<servers.length; i++) {
            if(servers[i] > maxWeight)
                maxWeight = servers[i];
        }
        return maxWeight;
    }

    /**
     * 求最大公约数
     * @return
     */
    private int getBCD() {
        int min = servers[0];
        for(int i=1; i<servers.length; i++) {
            if(servers[i]<min)
                min = servers[i];
        }

        while (true) {
            boolean isCommon = true;
            for(int i=0; i<servers.length; i++) {
                if(servers[i] % min !=0) {
                    isCommon = false;
                    break;
                }
            }
            if(isCommon) {
                return min;
            }
            min--;
        }
    }

    /**
     * 选择
     * @return
     */
    public int select() {
        while (true) {
            number = (number + 1) % servers.length;
            if(number == 0) {
                cw = cw - gcd;
                if(cw <= 0) {
                    cw = maxWeight;
                    if(cw==0) return -1;
                }
            }
            if(servers[number] >= cw)
                return number;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 4 };
        WeightedRoundRobin wrr = new WeightedRoundRobin(arr);
        for(int i=0; i<15; i++) {
            System.out.println(wrr.select());
        }
    }
}
