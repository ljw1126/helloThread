package com.example.hellothread.section8;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.hellothread.util.MyLogger.log;

public class BoundedQueueV5 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition producerCond = lock.newCondition();
    private final Condition consumerCond = lock.newCondition();

    private final Deque<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV5(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();
        try {
            while (queue.size() == max) {
                log("[put] 큐가 가득 참, 생산자 대기");
                producerCond.await(); // WAITING
                log("[put] 생산자 깨어남, " + Thread.currentThread().getName());
            }

            queue.offer(data);
            log("[put] 생산자 데이터 저장, signal() 호출");
            consumerCond.signal(); // 대기 스레드 깨움
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                log("[take] 큐에 데이터 없음, 소비자 대기");
                consumerCond.await();
                log("[take] 소비자 깨어남, " + Thread.currentThread().getName());
            }

            String data = queue.poll();
            log("[take] 소비자 데이터 획득, signal() 호출");
            producerCond.signal();
            return data;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
