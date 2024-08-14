package com.example.hellothread.section12.test;

public class NewOderServiceTestMain {
    public static void main(String[] args) {
        String orderNo = "Order#1234";
        NewOrderService orderService = new NewOrderService();
        orderService.order(orderNo);
    }
}
