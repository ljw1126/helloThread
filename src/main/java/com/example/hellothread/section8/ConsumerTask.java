package com.example.hellothread.section8;

import static com.example.hellothread.util.MyLogger.log;

public class ConsumerTask implements Runnable {
    private final BoundedQueue queue;

    public ConsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[소비 시도]  ? <- " + queue);
        log("[소비 완료] " + queue.take() + "<-" + queue);
    }
}
