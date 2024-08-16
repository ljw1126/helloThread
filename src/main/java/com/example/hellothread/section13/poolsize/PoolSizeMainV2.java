package com.example.hellothread.section13.poolsize;

import com.example.hellothread.section12.RunnableTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.example.hellothread.util.ExecutorUtils.printState;
import static com.example.hellothread.util.MyLogger.log;

public class PoolSizeMainV2 {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2); // 스레드 코어, 맥스 수가 2개 , 큐는 무제한
        //ExecutorService es = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        printState(es);

        for(int i = 1; i <= 6; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
