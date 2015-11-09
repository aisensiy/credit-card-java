package com.tw.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import static com.tw.domain.DateUtil.addMonth;
import static java.util.stream.Collectors.toList;

public class InstalmentRequest implements Request, Record {
    int id;
    private int amount;
    private Bill bill;
    private InstalmentPolicy policy;
    private RequestStatus status = RequestStatus.NEW;
    private Timestamp createdAt = new Timestamp(new java.util.Date().getTime());
    List<InstalmentItem> items = new ArrayList<>();

    public InstalmentRequest() {
    }

    public InstalmentRequest(int amount, Bill bill, InstalmentPolicy policy) {
        this.amount = amount;

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
        map.put("createdAt", createdAt);
        map.put("items", items.stream().map(InstalmentItem::toJson).collect(toList()));
        return map;
    }

    @Override
    public Map<String, Object> toRefJson() {
        return null;
    }

    public List<InstalmentItem> getItems() {
        return items;
    }

    public void createInstalmentItems() {
        int term = policy.getTerm();
        for (int i = 0; i < term; i++) {
            int amount = this.amount / term;
            int commission = (int) (0.01 * amount * policy.getCommission());
            Date repaymentDate = addMonth(i, bill.getRepaymentDay());
            items.add(new InstalmentItem(amount, commission, repaymentDate, this));
        }
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
