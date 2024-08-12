package com.example.hellothread.section10.cas;

import static com.example.hellothread.util.MyLogger.log;
import static com.example.hellothread.util.ThreadUtils.sleep;

public class SpinLockBad {
    private volatile  boolean lock = false;

    // 멀티스레드 접근시 락 사용 여부 확인 과 값 변경 부분이 원자적이지 않아 동시성 이슈 발생
    public void lock() {
        log("락 획득 시도");
        while(true) {
            if(!lock) { // 1. 락 사용 여부 확인
                sleep(100); // 문제 상황 확인용, 스레드 대기
                lock = true;
                break;
            } else {
                log("락 획득 실패 - 스핀 대기");
            }
        }
        log("락 획득 완료");
    }

    public void unlock() {
        lock = false; // 원자적 연산
        log("락 반납 완료");
    }
}
