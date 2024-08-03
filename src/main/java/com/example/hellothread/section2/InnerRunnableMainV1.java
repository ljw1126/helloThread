package com.example.hellothread.section2;

import static com.example.hellothread.util.MyLogger.*;

public class InnerRunnableMainV1 {
    public static void main(String[] args) {
        log("main() start");

        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        log("main() end");
    }

    static class MyRunnable implements  Runnable {
        @Override
        public void run() {
            log("run()");
        }
    }
}
