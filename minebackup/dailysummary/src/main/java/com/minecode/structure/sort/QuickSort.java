package com.minecode.structure.sort;

import java.util.Arrays;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/20
 * @desc
 */
public class QuickSort {
    //理解:在待排序的数据集中选取一个基准数,然后以这个基准数来分隔,左边都小于该基准数,右边都大于该基准数
    static int[] a = {26, 53, 67, 48, 57, 13, 48, 32, 60, 50};

    public void test(int[] b, int index, int length) {

    }

    public static void quickSort(int[] arr,int low,int high){
        int i,j,temp,t;
        if(low>high){
            System.out.println(low);
            System.out.println(high);
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }


    public static void main(String[] args){
        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        quickSort(arr, 0, arr.length-1);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
    }
}
