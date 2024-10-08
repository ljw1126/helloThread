package com.example.hellothread.section5;

import static com.example.hellothread.util.MyLogger.log;
import static com.example.hellothread.util.ThreadUtils.sleep;

public class VolatileCountMain {
    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        t.start();

        sleep(1000);

        task.flag = false;
        log("flag = " + task.flag + ", count = " + task.count + " in main()");
    }

    private static class MyTask implements Runnable {
        /*boolean flag = true;
        long count;*/
        volatile boolean flag = true;
        volatile long count;

        @Override
        public void run() {
            while(flag) {
                count += 1;
                // 1억번에 한번씩 출력
                if(count % 100_000_000 == 0) {
                    log("flag = " + flag + ", count = " + count + " in while()");
                }
            }
            log("flag = " + flag + ", count = " + count + " 종료");
        }
    }
}
