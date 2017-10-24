package com.minecode.study.thread;

import java.time.LocalTime;
import java.util.Locale;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/9/25
 * @desc
 */
public class ThreadTest extends Thread {
    @Override
    public void run() {
        LocalTime time = LocalTime.now();
        System.out.println(time);
    }

    public static void main(String[] args) {
        ThreadTest tt = new ThreadTest();
        ThreadTest tt1 = new ThreadTest();
        ThreadTest tt2 = new ThreadTest();
        ThreadTest tt3 = new ThreadTest();
        ThreadTest tt4 = new ThreadTest();
        System.out.println("进入主线程");
        tt.start();
        tt1.start();
        tt2.start();
        tt3.start();
        tt4.start();
        LocalTime time = LocalTime.now();


        System.out.println("main线程结束" + time);

    }
}
