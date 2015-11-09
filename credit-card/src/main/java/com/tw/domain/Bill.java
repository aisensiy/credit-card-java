package com.tw.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Bill {
    int id;
    private Timestamp createdAt;
    private Date billDate;
    List<BillItem> items;
    private int amount;
    private Consumer consumer;

    public Bill(int amount, Timestamp createdAt, Date billDate, Consumer consumer) {
        this.createdAt = createdAt;
        this.billDate = billDate;
        this.amount = amount;
        this.consumer = consumer;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Date getBillDate() {
        return billDate;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public Consumer getConsumer() {
        return consumer;
    }
}
