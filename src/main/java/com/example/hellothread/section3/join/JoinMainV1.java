package com.example.hellothread.section3.join;

import static com.example.hellothread.util.MyLogger.log;
import static com.example.hellothread.util.ThreadUtils.sleep;

public class JoinMainV1 {
    public static void main(String[] args) {
        log("start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");
        thread1.start();
        thread2.start();

        log("task1.result = " + task1.result);
        log("task2.result = " + task2.result);

        int sumAll = task1.result + task2.result;
        log("sum = " + sumAll);

        log("end");
    }

    static class SumTask implements Runnable {
        private final int start;
        private final int end;
        private int result;

        public SumTask(int start, int end) {
            this(start, end, 0);
        }

        public SumTask(int start, int end, int result) {
            this.start = start;
            this.end = end;
            this.result = result;
        }

        @Override
        public void run() {
            log("작업 시작");

            sleep(2000);
            int sum = 0;
            for(int i = start; i <= end; i++) {
                sum += i;
            }

            result = sum;
            log("작업 종료 : " + result);
        }
    }
}
