package com.example.hellothread.section2;

import com.example.hellothread.section2.util.MyLogger;

public class StartTest4Main {
    public static void main(String[] args) {
        PrintWork a = new PrintWork("A", 1000);
        PrintWork b = new PrintWork("B", 500);

        Thread threadA = new Thread(a);
        Thread threadB = new Thread(b);

        threadA.start();
        threadB.start();
    }

    static class PrintWork implements Runnable {
        private String content;
        private int sleepMs;

        public PrintWork(String content, int sleepMs) {
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run() {
            while(true) {
                MyLogger.log(content);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
