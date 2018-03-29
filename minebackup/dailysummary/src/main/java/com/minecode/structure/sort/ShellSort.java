package com.minecode.structure.sort;

import org.junit.Test;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/20
 * @desc
 */
public class ShellSort {
    //理解:基于插入排序,并在此基础上有所衍生,即假定需要排序的数相当多,那么整体数据相对有序的情况会更有效率,所以有了shell排序,
    //具体实现为先按固定的步长进行插入排序,再逐步缩短步长.直至步长为1

    static int[] b = {11, 10, 55, 78, 100, 111, 45, 56, 79, 90, 345, 1000};

    @Test
    public void test() {
        //按长度缩短步
        //按步循环
        //按步长插入排序

        for (int step = b.length / 2; step > 0; step /= 2) {
            for (int i = step; i < b.length; i++) {
                int temp = b[i];
                int j;
                for (j = i - step; j >= 0; j-=step) {
                    if (b[j] > temp) {
                        b[j + step] = b[j];
                    } else {
                        break;
                    }
                }
                b[j + step] = temp;
            }

        }
        for (int a : b) {
            System.out.println(a);
        }
    }

}
