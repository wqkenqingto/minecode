package com.minecode.structure.sort;

import org.junit.Test;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/20
 * @desc
 */
public class InsertSortBySelf {
    //理解:默认a[1,n]中左起a[1,i]已经排好序,那么[i,n]中的值依次与a[1,i]中值比较,若大于,则挪动相应
    //把待排序的数组，第0位的元素看做是一个排好序的数组，之后用temp记录数组的第1位，与前面的元素依次进行比较，如果小于前面的元素，那么就将前面的元素赋值给后边的元素，直到temp小于前面的某一个元素位置，将temp赋值给该位置。之后重复操作，将所有元素插入到适合的位置
    static int[] b = {11, 10, 55, 78, 100, 111, 45, 56, 79, 90, 345, 1000};
    static int[] a = {26, 53, 67, 48, 57, 13, 48, 32, 60, 50};

    @Test
    public void test() {
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (temp < a[j]) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = temp;
        }

        for (int s : a) {
            System.out.println(s);
        }
    }

}
