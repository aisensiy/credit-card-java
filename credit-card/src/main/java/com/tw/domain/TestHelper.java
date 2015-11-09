package com.tw.domain;

import java.sql.Timestamp;
import java.util.Date;

public class TestHelper {
    public static Consumer consumer(int id, String name) {
        Consumer consumer = new Consumer(name, 15, 3, 10000);
        consumer.id = id;
        return consumer;
    }

    public static PaymentRequest paymentRequest(int id, int amount, Consumer consumer) {
        PaymentRequest paymentRequest = new PaymentRequest(amount, new Timestamp(new Date().getTime()), consumer);
        paymentRequest.id = id;
        return paymentRequest;
    }
}
