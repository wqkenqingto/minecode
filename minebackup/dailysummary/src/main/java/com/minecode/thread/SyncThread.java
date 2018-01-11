package com.minecode.thread;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2017/12/11
 * @desc
 */
class SyncThread implements Runnable {

    private int tick = 50;

    Object obj = new Object();//申请一个对象

    @Override
    public void run() {

        while (true) {
            synchronized (obj) {
                if (tick > 0) {
                    //try{Thread.sleep(40);}catch(Exception e){}
                    System.out.println(Thread.currentThread().getName() + " sail --" + tick--);
                }
            }
        }
    }
}

class ThreadTestd {
    public static void main(String[] args) {

        Runnable a = () -> {
            Object o = new Object();
            synchronized (o) {
                long time = System.currentTimeMillis();
                System.out.println("ok" + time);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return;
        };

        Thread t = null;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 9; i++) {
            t = new Thread(a);
            executor.execute(t);
        }
        executor.shutdown();

    }


}
