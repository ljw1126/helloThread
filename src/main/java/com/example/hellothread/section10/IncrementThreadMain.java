package com.example.hellothread.section10;

import java.util.ArrayList;
import java.util.List;

import static com.example.hellothread.util.ThreadUtils.sleep;

public class IncrementThreadMain {

    private static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());
        test(new VolatileInteger()); // volatile 추가해도 멀티 스레드에 안전하지 않다
        test(new SyncInteger()); // 임계 영역 안에서 모니터 락 획득한 스레드 순으로 안전하게 실행
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sleep(10); // 다른 스레드와 동시 실행하기 위해 대기 시간 추가

                incrementInteger.increment();
            }
        };

        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for(Thread thread : threads) {
            thread.join();
        }

        int result = incrementInteger.get();
        System.out.println(incrementInteger.getClass().getSimpleName() + " result : " + result);
    }
}
