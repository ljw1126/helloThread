package com.example.hellothread.section12.callable;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.example.hellothread.util.MyLogger.log;

public class InvokeAnyMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(10);

        CallableTask task1 = new CallableTask("taks1");
        CallableTask task2 = new CallableTask("taks2", 2000);
        CallableTask task3 = new CallableTask("taks3", 3000);
        List<CallableTask> tasks = List.of(task1, task2, task3);

        Integer result = es.invokeAny(tasks); // 가장 먼저 완료된 작업 결과 반환, 나머지는 인터럽트로 취소
        log("value = " + result);

        es.close();
    }
}
