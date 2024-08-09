package com.example.hellothread.section8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.example.hellothread.util.MyLogger.log;

public class BoundedQueueV6_2 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_2(int max) {
        queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean result = queue.offer(data); // 가득 차면 false, 즉시 종료
        log("저장 시도 결과 = " + result);
    }

    @Override
    public String take() {
        return queue.poll(); // 없으면 null 반환
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
