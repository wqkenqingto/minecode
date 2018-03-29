package com.minecode.thread.pattern;

import java.util.LinkedList;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/21
 * @desc
 */
public class Costumer {
    public static void main(String[] args) {
        String s = "100001001101100110000";
        String[] ss = s.split("1");
        for (String d : ss) {
            System.out.println(d);
        }
    }
}
