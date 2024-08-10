package com.example.hellothread.section10;

public class BasicInteger implements IncrementInteger {
    private int value;

    @Override
    public void increment() {
        value += 1; // 원자적 연산 x, 멀티 스레드에 안전하지 않음
    }

    @Override
    public int get() {
        return value;
    }
}
