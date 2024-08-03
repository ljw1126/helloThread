package com.example.hellothread.section3;

import static com.example.hellothread.section2.util.MyLogger.log;

public class ThreadStateMain {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new MyRunnable(), "myThread");
        log("myThread.state1 = " + myThread.getState()); // NEW
        log("myThread.start()");
        myThread.start();
        Thread.sleep(1000);

        log("myThread.state3 = " + myThread.getState()); // TIME_WAITING
        Thread.sleep(4000);
        log("myThread.state5 = " + myThread.getState()); // TERMINATED
        log("end");
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            log("start");
            log("myThread.state2 = " + Thread.currentThread().getState()); //RUNNABLE
            try {
                log("sleep() start");
                Thread.sleep(3000);
                log("sleep() end");
                log("myThread.state4 = " + Thread.currentThread().getState()); //RUNNABLE
                log("end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
