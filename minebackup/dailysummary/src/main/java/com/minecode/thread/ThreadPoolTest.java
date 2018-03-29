package com.minecode.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/12/11
 * @desc 线程池的使用
 */
public class ThreadPoolTest {


    class TestThread implements Runnable {
        Integer a = 0;
        Object object = new Object();

        @Override
        public void run() {
            synchronized (object) {
                long time = System.currentTimeMillis();

//                System.out.println(time);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a++;
                System.out.println("there is " + a);
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        TestThread t = new ThreadPoolTest().new TestThread();
        for (int i = 0; i <= 20; i++) {
            executor.execute(new Thread(t));
//            new Thread(t).start();
        }
        executor.shutdown();

    }
}
