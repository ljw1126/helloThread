package com.example.hellothread.section13.reject;

import com.example.hellothread.section12.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectMainV2 {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());

        es.submit(new RunnableTask("task1"));
        es.submit(new RunnableTask("task2")); // DiscardPolicy 경우 거절된 작업을 무시하고, 아무런 예외 발생시키지 x
        es.submit(new RunnableTask("task3"));

        es.close();
    }
}
