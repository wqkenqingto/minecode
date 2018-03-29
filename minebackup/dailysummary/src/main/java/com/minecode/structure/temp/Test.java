package com.minecode.structure.temp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/22
 * @desc
 */
public class Test {
    public static void main(String[] args) {
        List list1 = new LinkedList();

        List list = new ArrayList();
        long t1 = returnTestTime(list);
        long t2 = returnTestTime(list1);

        System.out.println("ArrayList 花费的时间" + t1);
        System.out.println("LinkedList 花费的时间" + t2);
    }

    public static long returnTestTime(List list) {
        int count = 1;
        long before = System.currentTimeMillis();
        while (count < 500000) {
            list.add(Math.random() * 100);
            count++;
        }
        long after = System.currentTimeMillis();
        return after - before;
    }
}
