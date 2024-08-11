package com.example.hellothread.section11;

public class SimpleListMainV1 {
    public static void main(String[] args) throws InterruptedException {
        SimpleList list = new BasicList();
        list.add("A");
        list.add("B");
        System.out.println("list  = " + list);
    }
}
