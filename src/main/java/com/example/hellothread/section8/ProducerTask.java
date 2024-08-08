package com.example.hellothread.section8;

import com.example.hellothread.util.MyLogger;

import static com.example.hellothread.util.MyLogger.*;

public class ProducerTask implements Runnable{
    private final BoundedQueue queue;
    private final String request;

    public ProducerTask(BoundedQueue queue, String request) {
        this.queue = queue;
        this.request = request;
    }

    @Override
    public void run() {
        log("[생산 시도] " + request + "->" + queue);
        queue.put(request);
        log("[생산 완료] " + request + "->" + queue);
    }
}
