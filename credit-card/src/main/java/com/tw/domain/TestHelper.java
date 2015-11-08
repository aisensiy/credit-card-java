package com.tw.domain;

public class TestHelper {
    public static Consumer consumer(int id, String name) {
        Consumer consumer = new Consumer(name, 15, 3, 10000);
        consumer.id = id;
        return consumer;
    }
}
