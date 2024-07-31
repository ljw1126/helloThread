package com.example.hellothread;

import com.example.hellothread.util.MyLogger;

public class StartTest2Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new CounterRunnable());
        thread.start();
    }

    static class CounterRunnable implements Runnable {
        @Override
        public void run() {
            for(int i = 0; i < 5; i++) {
                MyLogger.log("value" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
