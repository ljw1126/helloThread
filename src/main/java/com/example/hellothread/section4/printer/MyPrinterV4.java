package com.example.hellothread.section4.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.example.hellothread.util.MyLogger.log;

public class MyPrinterV4 {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner sc = new Scanner(System.in);
        while(true) {
            log("프린터할 문서를 입려가헤숑. 종료 (q) : ");
            String input = sc.nextLine();
            if("q".equals(input)) {
                printerThread.interrupt(); // 추가
                break;
            }

            printer.addJob(input);
        }
    }

    static class Printer implements Runnable {
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {
            while(!Thread.interrupted()) { // 인터럽트 상태이면 true를 반환해서 종료, 그리고 false로 변경 됨
                if(jobQueue.isEmpty()) {
                    Thread.yield(); // *추가
                    continue;
                }

                try {
                    String job = jobQueue.poll();
                    log("출력 시작 : " + job + ", 대기 문서 : " + jobQueue);
                    Thread.sleep(3000); // 출력에 걸리는 시간
                    log("출력 완료 : " + job);
                } catch (InterruptedException e) {
                    log("인터럽트 !");
                    break;
                }
            }
            log("프린터 종료");
        }

        public void addJob(String input) {
            jobQueue.offer(input);
        }
    }
}
