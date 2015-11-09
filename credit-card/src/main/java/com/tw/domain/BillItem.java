package com.tw.domain;

import java.util.HashMap;
import java.util.Map;

public class BillItem implements Record {
    int id;
    private ItemType itemType;
    private Bill bill;
    private int amount;

    public BillItem(int amount, ItemType itemType, Bill bill) {
        this.amount = amount;
        this.itemType = itemType;
        this.bill = bill;
    }

    public BillItem (){}

    public int getId() {
        return id;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Bill getBill() {
        return bill;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public Map<String, Object> toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("itemType", itemType.toString());
        map.put("amount", amount);
        return map;
    }

    @Override
    public Map<String, Object> toRefJson() {
        return null;
    }
}
