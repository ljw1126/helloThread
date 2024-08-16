package com.example.hellothread.section13.poolsize;

import com.example.hellothread.section12.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static com.example.hellothread.util.ExecutorUtils.printState;
import static com.example.hellothread.util.MyLogger.log;
import static com.example.hellothread.util.ThreadUtils.sleep;

import java.util.concurrent.TimeUnit;

public class PoolSizeMainV3 {

    public static void main(String[] args) {
        //ExecutorService es = Executors.newCachedThreadPool();
        //캐시 스레드 풀 전략은 초과 스레드가 60초의 생존 주기를 가지지만, 예제에서는 확인을 위해 3s로 지정
        ThreadPoolExecutor es = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 3, TimeUnit.SECONDS, new SynchronousQueue<>());
        printState(es);

        for(int i = 1; i <= 4; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(es);

        sleep(3000);
        log("== maximumPoolSize 대기 시간 초과 ==");
        printState(es);

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
