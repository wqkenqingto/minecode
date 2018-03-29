package com.minecode.structure.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/20
 * @desc
 */
public class ChoiceSort {
    static int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};

    @Test
    public void test() {
        int count = 1;
        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            int tag = i;
            int c = i;
            while (a.length - tag > 1) {
                if (temp > a[tag + 1]) {
                    temp = a[tag + 1];
                    c = tag + 1;
                }
                tag++;
            }
            a[c] = a[i];
            a[i] = temp;
        }
        System.out.println(Arrays.toString(a));
    }
}