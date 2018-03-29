package com.minecode.test;

import sun.jvm.hotspot.utilities.BitMap;

import java.util.BitSet;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/15
 * @desc
 */
public class TestN {
    public static void main(String[] args) {
//        BitMap bitMap = new BitMap(10);
//        bitMap.atPut(1, true);
//        bitMap.atPut(2, false);
//        bitMap.atPut(3, false);
//        for (int i = 0; i < 100; i ++) {
//            if ((i & 1) == 0) { // 偶数
//                System.out.println(i);
//            }
//        }

//        int a = -15, b = 15;
//        System.out.println(~a + 1);
//        System.out.println(~b + 1);
        int a = 2;
        a = a << 21;
        System.out.println(a);

    }
}
