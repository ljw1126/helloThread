package com.example.hellothread.section2;

public class HelloRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": run()");
    }
}
