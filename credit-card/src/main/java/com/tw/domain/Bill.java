package com.tw.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class Bill implements Record {
    int id;
    private Timestamp createdAt;
    private Date billDay;
    List<BillItem> items = new ArrayList<>();
    private int amount;
    private Consumer consumer;
    private Date repaymentDay;

    public Bill(Timestamp createdAt, Consumer consumer) {
        this.createdAt = createdAt;
        this.consumer = consumer;
        initBillDayAndRepaymentDay(createdAt, consumer);
    }

    public Bill() {
    }

    public Bill(int amount, Timestamp createdAt, Consumer consumer) {
        this.createdAt = createdAt;
        this.amount = amount;
        this.consumer = consumer;

        initBillDayAndRepaymentDay(createdAt, consumer);
    }

    private void initBillDayAndRepaymentDay(Timestamp createdAt, Consumer consumer) {
        final int billDay = consumer.getBillDay();
        final int repaymentDay = consumer.getRepaymentDay();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createdAt);
        calendar.set(Calendar.DAY_OF_MONTH, billDay);
        this.billDay = new Date(calendar.getTime().getTime());
        if (billDay < repaymentDay) {
            this.repaymentDay = new Date(calendar.getTime().getTime());
        } else {
            calendar.add(Calendar.MONTH, 1);
        }
        calendar.set(Calendar.DAY_OF_MONTH, repaymentDay);
        this.repaymentDay = new Date(calendar.getTime().getTime());
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Date getBillDay() {
        return billDay;
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

    public Date getRepaymentDay() {
        return repaymentDay;
    }

    @Override
    public Map<String, Object> toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("amount", amount);
        map.put("repaymentDay", repaymentDay);
        map.put("billDay", billDay);
        map.put("items", items.stream().map(BillItem::toJson).collect(toList()));
        return map;
    }

    @Override
    public Map<String, Object> toRefJson() {
        return null;
    }

    public List<BillItem> getItems() {
        return items;
    }

    public void setBillItems(List<BillItem> billItems) {
        this.amount = 0;
        for (BillItem billItem : billItems)
            this.amount += billItem.getAmount();
        this.items = billItems;
    }
}
