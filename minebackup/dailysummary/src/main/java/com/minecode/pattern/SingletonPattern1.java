package com.minecode.pattern;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/21
 * @desc
 */
public class SingletonPattern1 {
    static SingletonPattern1 s1 = null;

    private SingletonPattern1() {

    }

    public static SingletonPattern1 newSingletonPattern1() {
        if (s1 == null) {
            s1 = new SingletonPattern1();
        }
        return s1;
    }

}
