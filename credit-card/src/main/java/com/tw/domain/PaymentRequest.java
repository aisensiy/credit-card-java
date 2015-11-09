package com.tw.domain;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class PaymentRequest implements Request, Record {
    int id;
    private Timestamp createdAt;
    private int amount;
    private RequestStatus status = RequestStatus.NEW;
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

    @Override
    public void confirm() {
        status = RequestStatus.CONFIRMED;
    }

    @Override
    public void reject() {
        status = RequestStatus.REJECTED;
    }

    @Override
    public Map<String, Object> toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("amount", amount);
        map.put("createdAt", createdAt);
        map.put("status", status.toString());
        map.put("consumer", consumeBy.toRefJson());
        return map;
    }

    @Override
    public Map<String, Object> toRefJson() {
        return null;
    }

    public RequestStatus getStatus() {
        return status;
    }
}
