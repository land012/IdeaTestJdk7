package com.umbrella.algorithm.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xudazhou on 2016/4/1.
 * 快递排序算法
 */
public class QuickSort {
    void quicksort(Integer[] arr, int i, int j) {
        if(i>=j) return;
        int start = i;
        int end = j;
        int privot = i;
        while (i<j) {
            while(arr[i]<arr[j] && i<j) {
                j--;
            }
            if(i>=j) break;

            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
            privot = j;
            i++;
            printArr(arr);

            while (arr[i]<arr[j] && i<j) {
                i++;
            }
            if(i>=j) break;
            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
            privot = i;
            j--;
            printArr(arr);
        }
        if(privot-1>start) quicksort(arr, start, privot-1);
        if(privot+1<end) quicksort(arr, privot+1, end);
    }

    <T> void printArr(T[] arr) {
        List<T> l1 = new ArrayList<>();
        for(int i=0; i<arr.length; i++) {
            l1.add(arr[i]);
        }
        System.out.println(l1);
    }

    @Test
    public void test1() {
        Integer[] arr1 = { 4, 6, 1, 2, 7, 3, 5 };
        quicksort(arr1, 0, arr1.length - 1);
        printArr(arr1);
    }

}
