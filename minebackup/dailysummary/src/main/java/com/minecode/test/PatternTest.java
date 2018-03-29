package com.minecode.test;

import com.minecode.pattern.SingletonPattern;
import com.minecode.pattern.SingletonPattern1;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/21
 * @desc
 */
public class PatternTest {
    public static void main(String[] args) {
//        SingletonPattern single = SingletonPattern.newSingleton();
//        SingletonPattern single2 = SingletonPattern.newSingleton();
//        if (single == single) {
//            System.out.println("是一个对象");
//        }
        SingletonPattern1 s1 = SingletonPattern1.newSingletonPattern1();
        SingletonPattern1 s2 = SingletonPattern1.newSingletonPattern1();
        if (s1 == s2) {
            System.out.println("是一个对象");
        }

    }
}
