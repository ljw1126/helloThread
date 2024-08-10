package com.example.hellothread.section10;

public class IncrementPerformanceMain {
    
    private static final long COUNT = 100_000_000;
    
    public static void main(String[] args) {
        test(new BasicInteger());
        test(new VolatileInteger()); // volatile 추가해도 멀티 스레드에 안전하지 않다
        test(new SyncInteger()); // 임계 영역 안에서 모니터 락 획득한 스레드 순으로 안전하게 실행
        test(new MyAtomicInteger());
    }

    private static void test(IncrementInteger incrementInteger) {
        long start = System.currentTimeMillis();

        for(int i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }

        long end = System.currentTimeMillis();
        System.out.println(incrementInteger.getClass().getSimpleName() + ": ms= " + (end - start));
    }
}
