package com.minecode.thread.create;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2018/3/21
 * @desc
 */
public class ThreadDemo extends Thread {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        ThreadDemo threadDemo1 = new ThreadDemo();
        ThreadDemo threadDemo2 = new ThreadDemo();
        threadDemo.start();
        threadDemo1.start();
        threadDemo2.start();

    }
    @Override
    public void run() {
        System.out.println("新建线程" + System.currentTimeMillis());
    }
}
