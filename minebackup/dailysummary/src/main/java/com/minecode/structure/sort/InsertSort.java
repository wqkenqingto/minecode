package com.minecode.structure.sort;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/20
 * @desc
 */
public class InsertSort {
    static int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};


    //插入排序
    public static void Sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tempdata = arr[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > tempdata) {
                    arr[j + 1] = arr[j];
                    System.out.println(j);
                } else {
                    break;
                }
            }
            System.out.println(j + 1);
            arr[j + 1] = tempdata;
        }
    }

    //输出打印
    public static void output(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    public static void main(String[] args) {
//        InsertSort.output(a);
        InsertSort.Sort(a);
//        InsertSort.output(a);
    }
}
