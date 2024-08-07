package com.example.hellothread.section6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.hellothread.util.MyLogger.log;
import static com.example.hellothread.util.ThreadUtils.sleep;

public class BankAccountV5 implements BankAccount{

    private int balance;

    // 비공정 락
    private final Lock lock = new ReentrantLock();

    public BankAccountV5(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작 : " + getClass().getSimpleName());

        if(!lock.tryLock()) { // 락 획득을 시도하고 즈기 성공 여부를 반환한다. (성공시 true, 실패시 false)
            log("[진입 실패] 이미 처리중인 작업이 있습니다");
            return false;
        }

        //==임계 영역 시작==
        try {
            log("[검증 시작] 출금액 : " + amount + ", 잔액 : " + balance);
            if (balance < amount) {
                log("[검증 실패] 출금액 : " + amount + ", 잔액 : " + balance);
                return false;
            }

            log("[검증 완료] 출금액 : " + amount + ", 잔액 : " + balance);
            sleep(1000); // 출금에 걸리는 시간으로 가정
            balance -= amount;
            log("[출금 완료] 출금액 : " + amount + ", 잔액 : " + balance);
            //==임계 영역 시작==
        } finally {
            lock.unlock(); // ReentrantLock 이용하여 lock 해제
        }

        log("거래 종료");
        return true;
    }

    @Override
    public int getBalance() {
        lock.lock(); // ReentrantLock 이용하여 lock 걸기
        try {
            return balance;
        } finally {
            lock.unlock(); // ReentrantLock 이용하여 lock 해제
        }
    }
}
