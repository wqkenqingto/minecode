package com.minecode.structure.lintcode.pratice;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/26
 * @desc
 */
public class InsertSort {

    public void sort() {
        int[] a = {9, 3, 2, 4, 8};

        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            if (temp < a[i - 1]) {
                a[i - 1] = temp;
                a[i] = a[i - 1];
            }

        }

    }
}
