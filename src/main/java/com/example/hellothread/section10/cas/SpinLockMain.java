package com.example.hellothread.section10.cas;

import static com.example.hellothread.util.MyLogger.log;
import static com.example.hellothread.util.ThreadUtils.sleep;

public class SpinLockMain {
    public static void main(String[] args) {
        //SpinLockBad spinLock  = new SpinLockBad();
        SpinLock spinLock  = new SpinLock();

        Runnable runnable = () -> {
            spinLock.lock();
            try {
                //critical section
                log("비즈니스 로직 실행");
                //sleep(1); // 오래 걸리는 로직에서는 스핀 락 사용 x, 하드웨어 적으로 cpu가 계속 돌고 있어서 지연시 스핀 대기 여러번 수행됨
            } finally {
                spinLock.unlock();
            }
        };

        Thread t1 = new Thread(runnable, "Thread-1");
        Thread t2 = new Thread(runnable, "Thread-2");

        t1.start();
        t2.start();
    }
}
