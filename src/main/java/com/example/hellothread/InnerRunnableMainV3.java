package com.example.hellothread;

import static com.example.hellothread.util.MyLogger.log;

public class InnerRunnableMainV3 {
    public static void main(String[] args) {
        log("main() start");

        Thread thread = new Thread(() -> log("run()"));
        thread.start();

        log("main() end");
    }


}