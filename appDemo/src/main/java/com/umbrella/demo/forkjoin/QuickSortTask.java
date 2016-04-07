package com.umbrella.demo.forkjoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Created by xudazhou on 2016/4/1.
 * 快速排序算法
 */
public class QuickSortTask extends RecursiveTask<Integer[]> {

    private static final Logger log = LoggerFactory.getLogger(QuickSortTask.class);

    private Integer[] arr;
    private int start;
    private int end;

    public QuickSortTask(Integer[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer[] compute() {
        int i = start;
        int j = end;
        int privot = i;
        log.info("My privot =" + arr[privot]);
        while(i<j) {
            while(arr[i]<=arr[j] && i<j) {
                j--;
            }
            if(i>=j) break;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            privot = j;
            i++;
            log.info(Arrays.toString(arr));

            while(arr[i]<=arr[j] && i<j) {
                i++;
            }
            if(i>=j) break;
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            privot = i;
            j--;
            log.info(Arrays.toString(arr));
        }
        QuickSortTask task1 = null;
        QuickSortTask task2 = null;
        if(privot-1 > start) {
            task1 = new QuickSortTask(arr, start, privot-1);
            task1.fork();
        }
        if(privot+1 < end) {
            task2 = new QuickSortTask(arr, privot+1, end);
            task2.fork();
        }
        if(task1!=null) task1.join();
        if(task2!=null) task2.join();

        return arr;
    }
}
