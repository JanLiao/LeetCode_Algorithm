package com.jan.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    private volatile int num = 0;
    public void testPool(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 100, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));
        for(int i = 0; i < 500; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    num++;
                }
            });
        }
        System.out.println("num = " + num);
        executor.shutdown();
    }
    public static void main(String[] args) {
        Test t = new Test();
        t.testPool();
    }
}
