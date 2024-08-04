package com.example.hellothread.section4.yield;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for(int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for(int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "-" + i);

                // 1. empty , 아무것도 하지 않는 경우
                //sleep(1); // 2. sleep 사용하는 경우
                Thread.yield(); // 3. yield 사용하는 경우
            }
        }
    }
}
