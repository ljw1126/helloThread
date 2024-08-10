package com.example.hellothread.section10;

public class SyncInteger implements IncrementInteger {
    private int value;

    @Override
    public synchronized void increment() {
        value += 1; // 임계 영역, 모니터 락 획득한 스레드만 작업 가능
    }

    @Override
    public synchronized int get() {
        return value;
    }
}
