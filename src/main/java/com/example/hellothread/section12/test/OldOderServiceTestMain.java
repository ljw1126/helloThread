package com.example.hellothread.section12.test;

public class OldOderServiceTestMain {
    public static void main(String[] args) {
        String orderNo = "Order#1234";
        OldOrderService orderService = new OldOrderService();
        orderService.order(orderNo);
    }
}
