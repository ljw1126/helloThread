package com.example.hellothread.section13.poolsize;

import com.example.hellothread.section12.RunnableTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

import static com.example.hellothread.util.ExecutorUtils.printState;
import static com.example.hellothread.util.MyLogger.log;
import static com.example.hellothread.util.ThreadUtils.sleep;

public class PoolSizeMainV1 {
    /**
     * corePoolSize = 2, maximumPoolSize=4
     * - 기본 스레드 2개, 최대 스레드 4개
     * - ArrayBlockingQueue 크기가 2인데 기본 스레드 2개에 큐에 2개가 대기하게 되면 최대 스레드 개수 4개까지 "초과 스레드" 생성됨
     *
     * keepAliveTime : 3s
     * - 초과 스레드가 생존할 수 있는 대기 시간을 뜻함. 이 시간 동안 처리할 작업이 없으면 초과 스레드는 제거된다
     * - 초과 스레드가 3s간 작업을 하지 않고 대기한다면 초과 스레드는 스레드 풀에서 제거된다
     */
    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ExecutorService es = new ThreadPoolExecutor(2, 4, 3000, TimeUnit.MILLISECONDS, workQueue);
        printState(es);

        es.execute(new RunnableTask("task1"));
        printState(es, "task1");

        es.execute(new RunnableTask("task2"));
        printState(es, "task2");

        es.execute(new RunnableTask("task3"));
        printState(es, "task3");

        es.execute(new RunnableTask("task4"));
        printState(es, "task4");

        es.execute(new RunnableTask("task5"));
        printState(es, "task5");

        es.execute(new RunnableTask("task6"));
        printState(es, "task6");

        try {
            es.execute(new RunnableTask("task7"));
        } catch (RejectedExecutionException e) {
            log("task7 실행 거절 예외 발생 : " + e);
        }

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(es);

        sleep(3000); // 초과 스레드 삭제 확인하기 위한 3s 대기 시간, 4개 -> 기본 2개로 스레드 pool 수 감소
        log("== maximumPoolSize 대기 시간 초과 ==");
        printState(es);

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
