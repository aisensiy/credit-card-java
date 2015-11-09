package com.tw.domain;

import java.sql.Timestamp;
import java.util.Date;

import static java.util.Arrays.asList;

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

    public static Bill bill(int id, int amount, Timestamp createdAt, java.sql.Date billDay, Consumer consumer) {
        Bill bill = new Bill(amount, createdAt, billDay, consumer);
        bill.id = id;
        return bill;
    }

    public static InstalmentPolicy instalmentPolicy(int id, InstalmentPolicy instalmentPolicy) {
        instalmentPolicy.id = id;
        return instalmentPolicy;
    }

    public static InstalmentRequest instalmentRequest(int id, InstalmentRequest instalmentRequest) {
        instalmentRequest.id = id;
        return instalmentRequest;
    }

    public static Bill billWithItems(int id, int amount, Timestamp createdAt, java.sql.Date billDay, Consumer consumer) {
        Bill bill = new Bill(amount, createdAt, billDay, consumer);
        bill.id = id;
        bill.items = asList(
                new BillItem(500, ItemType.INSTALMENT, bill),
                new BillItem(500, ItemType.PAYMENT, bill)
        );
        return bill;
    }
}
