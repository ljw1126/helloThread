package com.example.hellothread.section4;

import static com.example.hellothread.util.MyLogger.log;
import static com.example.hellothread.util.ThreadUtils.sleep;

public class ThreadStopMainV3 {
    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask, "work");
        thread.start();

        sleep(100);
        log("작업 중단 지시 thread.interrupt()");
        thread.interrupt();
        log("work 스레드 인터럽트 상태 1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                log("작업 중");
            }

            log("work 스레드 인터럽트 상태 2 = " + Thread.currentThread().isInterrupted());

            try {
                log("자원 정리 시도");
                Thread.sleep(3000);
                log("자원 정리 완료");
            } catch (InterruptedException e) {
                log("자원 정리 실패 - 자원 정리 중 인터럽트 발생");
                log("work 스레드 인터럽트 상태 3 = " + Thread.currentThread().isInterrupted());
            }
            log("작업 종료");
        }
    }
}
