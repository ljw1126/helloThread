package com.example.hellothread.section3;

import static com.example.hellothread.util.ThreadUtils.sleep;

public class CheckedExceptionMain {
    public static void main(String[] args) {

    }

    static class CheckedRunnable implements Runnable {
        @Override
        public void run() {
            //throw new Exception(); 주석 풀면 예외 발생
            sleep(1000);
        }
    }
}
