package com.example.hellothread.section12.callable;

import java.util.concurrent.Callable;

import static com.example.hellothread.util.MyLogger.log;
import static com.example.hellothread.util.ThreadUtils.sleep;

public class CallableTask implements Callable<Integer> {
    private final String name;
    private final int sleepMs;

    public CallableTask(String name) {
        this(name, 1000);
    }

    public CallableTask(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public Integer call() throws Exception {
        log(name + " 실행");
        sleep(sleepMs);
        log(name + " 완료, return = " + sleepMs);
        return sleepMs;
    }
}
