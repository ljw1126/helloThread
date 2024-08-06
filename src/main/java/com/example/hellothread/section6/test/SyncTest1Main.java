package com.example.hellothread.section6.test;

import com.example.hellothread.util.MyLogger;

public class SyncTest1Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        MyLogger.log("결과 : " + counter.getCount());
    }

    static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count += 1;
        }

        public synchronized int getCount() {
            return count;
        }
    }
}
