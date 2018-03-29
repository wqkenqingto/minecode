package com.minecode.pattern;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/21
 * @desc
 */
public class SingletonPattern {
    //单例模式,饿汉模式
    static SingletonPattern single = new SingletonPattern();

    private SingletonPattern() {

    }

    public static SingletonPattern newSingleton() {

        return single;
    }
}
