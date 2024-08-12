package com.example.hellothread.section8;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.example.hellothread.util.MyLogger.log;

public class BoundedQueueV1 implements BoundedQueue {
    private final Deque<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV1(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        if(queue.size() == max) {
            log("[put] 큐가 가득 참, 버림 : " + data);
            return;
        }

        queue.offer(data);
    }

    @Override
    public String take() {
        if(queue.isEmpty()) {
            return null;
        }

        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
