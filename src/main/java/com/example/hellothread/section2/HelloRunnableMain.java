package com.example.hellothread.section2;

public class HelloRunnableMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        // 스레드와 해당 스레드가 실행할 작업이 서로 분리되어 있다는 점이 차이
        HelloRunnable runnable = new HelloRunnable(); // 실행할 작업
        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
