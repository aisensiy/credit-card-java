package com.tw.domain;

import java.sql.Timestamp;

public class PaymentRequest {
    int id;
    private Timestamp createdAt;
    private int amount;
    Consumer consumeBy;

    public PaymentRequest(int amount, Timestamp createdAt, Consumer consumeBy) {
        this.createdAt = createdAt;
        this.amount = amount;
        this.consumeBy = consumeBy;
    }

    public int getId() {
        return id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public int getAmount() {
        return amount;
    }

    public Consumer getConsumeBy() {
        return consumeBy;
    }
}
