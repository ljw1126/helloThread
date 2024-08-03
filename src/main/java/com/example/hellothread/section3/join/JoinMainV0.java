package com.example.hellothread.section3.join;

import com.example.hellothread.util.MyLogger;
import com.example.hellothread.util.ThreadUtils;

import static com.example.hellothread.util.MyLogger.*;
import static com.example.hellothread.util.ThreadUtils.*;

public class JoinMainV0 {
    public static void main(String[] args) {
        log("start");
        Thread thread1 = new Thread(new Job(), "thread-1");
        Thread thread2 = new Thread(new Job(), "thread-2");

        thread1.start();
        thread2.start();
        log("end");
    }

    static class Job implements Runnable {
        @Override
        public void run() {
            log("작업 시작");
            sleep(2000);
            log("작업 완료");
        }
    }
}