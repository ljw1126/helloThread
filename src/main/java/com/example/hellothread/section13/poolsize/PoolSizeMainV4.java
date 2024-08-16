package com.example.hellothread.section13.poolsize;

import com.example.hellothread.section12.RunnableTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.example.hellothread.util.ExecutorUtils.printState;
import static com.example.hellothread.util.MyLogger.log;
import static com.example.hellothread.util.ThreadUtils.sleep;

public class PoolSizeMainV4 {

    //static final int TASK_SIZE = 1100; // 1. 일반 상황
    //static final int TASK_SIZE = 1200; // 2. 긴급
    static final int TASK_SIZE = 1201; // 3. 거절

    public static void main(String[] args) {
        // 코어 스레드 100개 실행 중이고, 큐에 1000개가 대기하면 추가로 100개 (max - core) 스레드 만들어져서 싫행
        // 1200까지는 괜찮은데, 1201이면 1개가 초과하니 reject
        ExecutorService es = new ThreadPoolExecutor(100, 200, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
        printState(es);

        long start = System.currentTimeMillis();

        for(int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;
            try {
                es.execute(new RunnableTask(taskName));
                printState(es, taskName);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }

        long end = System.currentTimeMillis();

        es.close();
        log("time : " + (end - start) + "ms");
        printState(es);
    }
}
