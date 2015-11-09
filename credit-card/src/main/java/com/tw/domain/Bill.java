package com.tw.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
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
    private Date repaymentDate;

    public Bill(Timestamp createdAt, Consumer consumer) {
        this.createdAt = createdAt;
        this.consumer = consumer;
        initBillDayAndRepaymentDay(createdAt, consumer);
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
        this.billDate = new Date(calendar.getTime().getTime());
        if (billDay < repaymentDay) {
            this.repaymentDate = new Date(calendar.getTime().getTime());
        } else {
            calendar.add(Calendar.MONTH, 1);
        }
        calendar.set(Calendar.DAY_OF_MONTH, repaymentDay);
        this.repaymentDate = new Date(calendar.getTime().getTime());
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

    public Date getRepaymentDate() {
        return repaymentDate;
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
