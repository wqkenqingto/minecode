package com.minecode.structure.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/20
 * @desc
 */
public class BubbleSort {
    //理解:依次比较相邻元素大小,较大值后排.每次排序就能获得当次最大值
    static int[] b = {11, 10, 55, 78, 100, 111, 45, 56, 79, 90, 345, 1000};

    @Test
    public void test() {
        for (int i = 0; i < b.length; i++) {
            for (int j = 1; j < b.length - i; j++) {
                if (b[j - 1] > b[j]) {
                    int temp;
                    temp = b[j - 1];
                    b[j - 1] = b[j];
                    b[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(b));
    }
}
