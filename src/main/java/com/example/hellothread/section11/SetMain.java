package com.example.hellothread.section11;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {
    public static void main(String[] args) {
        Set<Integer> copySet = new CopyOnWriteArraySet<>(); // HashSet 대안
        copySet.add(1);
        copySet.add(2);
        copySet.add(3);
        System.out.println(copySet);

        Set<Integer> skipSet = new ConcurrentSkipListSet<>(); // TreeSet 대안, 데이터 정렬 순서 유지
        skipSet.add(3);
        skipSet.add(1);
        skipSet.add(2);
        System.out.println(skipSet);
    }
}
