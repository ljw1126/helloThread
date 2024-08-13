package com.example.hellothread.section12;

import java.util.Random;

import static com.example.hellothread.util.MyLogger.log;
import static com.example.hellothread.util.ThreadUtils.sleep;

public class RunnableMain {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable task = new MyRunnable();
        Thread t = new Thread(task, "Thread-1");
        t.start();
        t.join();

        int result = task.value;
        log("result value = " + result);
    }

    static class MyRunnable implements Runnable {
        private int value;
        @Override
        public void run() {
            log("Runnable 시작");
            sleep(2000);
            value = new Random().nextInt(10);
            log("create value = " + value);
            log("Runnable 완료");
        }
    }
}
