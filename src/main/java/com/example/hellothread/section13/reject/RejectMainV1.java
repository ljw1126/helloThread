package com.example.hellothread.section13.reject;

import com.example.hellothread.section12.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

import static com.example.hellothread.util.MyLogger.log;

public class RejectMainV1 {
    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());

        es.submit(new RunnableTask("task1"));

        try {
            es.submit(new RunnableTask("task2")); // 스레드가 1개 인데 두번째 넣으면 정책에 따라 RejectedExecutionException 예외 던짐
        } catch (RejectedExecutionException e) {
            log("요청 초과");
            // 포기, 다시 시도 등 다양한 고민을 하면 됨
            log(e);
        }

        es.close();
    }
}
