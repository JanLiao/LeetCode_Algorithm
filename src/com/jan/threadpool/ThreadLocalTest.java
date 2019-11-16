package com.jan.threadpool;

import com.sun.javaws.IconUtil;

import java.util.Random;

public class ThreadLocalTest {
    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    public void start(){
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    threadLocal.set(new Random().nextInt(10));
                    System.out.println(threadLocal.get());
                    threadLocal.remove();
                }
            }).start();
        }
    }
    public int binarySearch(int[] nums, int tartget){
        if(nums.length == 0){
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int idx = -1;
        while(true){
            if(left > right){
                break;
            }
            int center = (left + right) / 2;
            if(nums[center] == tartget) return center;
            if(nums[center] > tartget){
                right = center - 1;
                continue;
            }
            if(nums[center] < tartget){
                left = center + 1;
                continue;
            }
        }
        return idx;
    }

    public static void main(String[] args) {
        ThreadLocalTest tlt = new ThreadLocalTest();
        //tlt.start();
        int[] arr = {0, 2, 3, 5};
        int idx = tlt.binarySearch(arr, 3);
        System.out.println(idx);
    }
}
