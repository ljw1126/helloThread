package com.example.hellothread.section8;

import java.util.ArrayList;
import java.util.List;

import static com.example.hellothread.util.MyLogger.log;
import static com.example.hellothread.util.ThreadUtils.sleep;

public class BoundedMain {
    public static void main(String[] args) {
        // 1. BoundedQueue 선택
        //BoundedQueue queue = new BoundedQueueV1(2); // 크기 2 제한
        //BoundedQueue queue = new BoundedQueueV2(2); // sleep(..), TIME_WAITING 상태에서 lock을 가지고 있어, 다른 스레드 모두 BLOCKED 상태 되어버림
        BoundedQueue queue = new BoundedQueueV3(2);

        // 2. 생산자, 소비자 실행 순서 선택 (이때 반드시 하나만 선택!)
        //producerFirst(queue); // 생산자 먼저 실행
        consumerFirst(queue); // 소비자 먼저 실행
    }

    private static void producerFirst(BoundedQueue queue) {
        log("== [생산자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + "==");

        List<Thread> threads = new ArrayList<>();
        startProducer(queue, threads); // 생산자 스레드 생성, 실행
        printAllState(queue, threads); // 스레드 상태 출력
        startConsumer(queue, threads); // 소비자 스레드 생성, 실행
        printAllState(queue, threads);
        log("== [생산자 먼저 실행] 종료, " + queue.getClass().getSimpleName() + "==");
    }

    private static void consumerFirst(BoundedQueue queue) {
        log("== [소비자 먼저 실행] 시작, " + queue.getClass().getSimpleName() + "==");

        List<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads); // 소비자 스레드 생성, 실행
        printAllState(queue, threads); // 스레드 상태 출력
        startProducer(queue, threads); // 생산자 스레드 생성, 실행
        printAllState(queue, threads);
        log("== [소비자 먼저 실행] 종료, " + queue.getClass().getSimpleName() + "==");
    }

    private static void startProducer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("생산자 시작");
        for(int i = 1; i <= 3; i++) {
            Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i); // data*를 추가하는 생산자 스레드
            threads.add(producer);
            producer.start();
            sleep(100);
        }
    }

    private static void printAllState(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("현재 상태 출력, 큐 데이터 : " + queue);
        for(Thread t : threads) {
            log(t.getName() + " : " + t.getState());
        }
    }

    private static void startConsumer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("소비자 시작");
        for(int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i); // data*를 추가하는 생산자 스레드
            threads.add(consumer);
            consumer.start();
            sleep(100);
        }

    }
}
