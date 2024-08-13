package com.example.hellothread.section12.callable;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.example.hellothread.util.MyLogger.log;

public class InvokeAllMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(10);

        CallableTask task1 = new CallableTask("taks1");
        CallableTask task2 = new CallableTask("taks2", 2000);
        CallableTask task3 = new CallableTask("taks3", 3000);
        List<CallableTask> tasks = List.of(task1, task2, task3);

        List<Future<Integer>> futures = es.invokeAll(tasks); // 모든 작업이 완료 될 때까지 대
        for(Future<Integer> future : futures) {
            log("value = " + future.get());
        }
        es.close();
    }
}
