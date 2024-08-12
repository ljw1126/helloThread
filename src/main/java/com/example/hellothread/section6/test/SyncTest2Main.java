package com.example.hellothread.section6.test;

import static com.example.hellothread.util.MyLogger.log;

public class SyncTest2Main {
    public static void main(String[] args) throws InterruptedException {
        MyCounter myCounter = new MyCounter();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                myCounter.count();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }

    static class MyCounter {
        public void count() {
            int localValue = 0;
            for(int i = 0; i < 1000; i++) {
                localValue += 1;
            }

            log("결과 : " + localValue);
        }
    }
}
