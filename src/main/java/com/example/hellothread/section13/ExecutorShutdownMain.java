package com.example.hellothread.section13;

import ch.qos.logback.core.util.TimeUtil;
import com.example.hellothread.section12.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.hellothread.util.ExecutorUtils.printState;
import static com.example.hellothread.util.MyLogger.log;

import java.util.concurrent.TimeUnit;

public class ExecutorShutdownMain {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("longTask", 100_000)); // 100초 대기

        printState(es);

        log("== shutdown 시작 == ");
        shutdownAndAwaitTermination(es);
        log("== shutdown 종료 == ");
        printState(es);
    }

    static void shutdownAndAwaitTermination(ExecutorService es) {
        es.shutdown(); // non-blocking, 새로운 작업을 받지 않는다. 처리 중이거나 큐에 대기중인 작업은 처리한다. 이후에 풀의 스레드 종료

        try {
            // 이미 대기 중인 작업들을 모두 완료 할때까지 10초 기다린다
            log("서비스 정상 종료 시도");
            if (!es.awaitTermination(10, TimeUnit.SECONDS)) { // blocking method
                // 정상 종료가 너무 오래 걸리면..
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                es.shutdownNow(); // 즉시 종료, 인터럽트 걸어서, non-blocking
                //작업이 취소될떄까지 대기한다
                if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("서비스가 종료되지 않습니다");
                }
            }
        } catch (InterruptedException e) {
            // awaitTermination()으로 대기중인 현재 스레드가 인터럽트 될 수 있다.
            es.shutdownNow();
        }
    }
}
