package com.example.hellothread.section11;

import java.util.ArrayList;
import java.util.List;

public class SimpleListMainV0 {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();

        Runnable addA = () -> list.add("A");
        Runnable addB = () -> list.add("B");
        Thread threadA = new Thread(addA);
        Thread threadB = new Thread(addB);

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println(list); // 스레드가 동시에 실행된다 했을때 [B, A]로 데이터거 저장 될 수도 있다
    }
}
