package com.tw.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class InstalmentRequest implements Request, Record {
    int id;
    private Bill bill;
    private final InstalmentPolicy policy;
    private RequestStatus status = RequestStatus.NEW;
    List<InstalmentItem> items;

    public InstalmentRequest(Bill bill, InstalmentPolicy policy) {

        this.bill = bill;
        this.policy = policy;
    }

    public Bill getBill() {
        return bill;
    }

    public int getId() {
        return id;
    }

    public RequestStatus getStatus() {
        return status;
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
        map.put("status", status);
        map.put("bill", bill.toRefJson());
        map.put("policy", policy.toJson());
        map.put("items", items.stream().map(InstalmentItem::toJson).collect(toList()));
        return map;
    }

    @Override
    public Map<String, Object> toRefJson() {
        return null;
    }
}
