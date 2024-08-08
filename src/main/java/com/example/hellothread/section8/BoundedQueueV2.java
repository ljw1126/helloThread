package com.example.hellothread.section8;

import com.example.hellothread.util.MyLogger;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.example.hellothread.util.MyLogger.*;
import static com.example.hellothread.util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {
    private final Deque<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while(queue.size() == max) {
            log("[put] 큐가 가득 참, 생산자 대기");
            sleep(1000);
        }

        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        while(queue.isEmpty()) {
            log("[take] 큐에 데이터 없음, 소비자 대기");
            sleep(1000);
        }

        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
