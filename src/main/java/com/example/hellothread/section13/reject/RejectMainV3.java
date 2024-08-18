package com.example.hellothread.section13.reject;

import com.example.hellothread.section12.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectMainV3 {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

        es.submit(new RunnableTask("task1"));
        es.submit(new RunnableTask("task2")); // 요청 스레드(main)가 직접 작업을 수행한다. 이로 인해 새로운 작업을 제출하는 스레드의 속도가 느려질 수 있다
        es.submit(new RunnableTask("task3"));
        es.submit(new RunnableTask("task4"));

        es.close();
    }
}
