package com.tw.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class Bill implements Record {
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

    @Override
    public Map<String, Object> toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("amount", amount);
        map.put("billDate", billDate);
        map.put("items", items.stream().map(BillItem::toJson).collect(toList()));
        return map;
    }

    @Override
    public Map<String, Object> toRefJson() {
        return null;
    }
}
