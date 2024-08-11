package com.example.hellothread.section11;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListMain {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(); // ArrayList 대안, 멀티 스레드 안전
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
    }
}
