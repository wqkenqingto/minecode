package com.minecode.java8;

import java.util.Arrays;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/9/25
 * @desc
 */
public class Test {

    public static void main(String[] args) {
        args = new String[]{"abc", "cde", "efg"};
        Arrays.asList(args).forEach(s -> System.out.println(s));
        
    }
}
