package com.minecode.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/9/25
 * @desc
 */
public class Test {

    public static void main(String[] args) {
//        args = new String[]{"abc", "cde", "efg"};
//        Arrays.asList(args).forEach(s -> System.out.println(s));
        System.out.println("测试开始");

        List<String> alist = new ArrayList<String>();
        String s = "t";
        int a = 1;
        for (int i = 0; i < 10000000; i++) {
            alist.add(s + 1);
        }
        long begin = System.currentTimeMillis();
        for (int j = 0; j < alist.size(); j++) {
            System.out.println("");
        }
        long end = System.currentTimeMillis();
        System.out.println("test1耗时" + (end - begin));
        begin = System.currentTimeMillis();
//        alist.stream().parallel().forEach(s1 -> System.out.print(""));
        end = System.currentTimeMillis();
        System.out.println("test1耗时" + (end - begin));
    }

}
