package com.tw.domain;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class InstalmentItem implements Record {
    int id;
    private int amount;
    private int commission;
    private Date repaymentDay;
    private InstalmentRequest instalmentRequest;

    public InstalmentItem(int amount, int commission, Date repaymentDay, InstalmentRequest instalmentRequest) {
        this.amount = amount;
        this.commission = commission;
        this.repaymentDay = repaymentDay;
        this.instalmentRequest = instalmentRequest;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public int getCommission() {
        return commission;
    }

    public Date getRepaymentDay() {
        return repaymentDay;
    }

    @Override
    public Map<String, Object> toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("repaymentDay", repaymentDay);
        map.put("commission", commission);
        map.put("amount", amount);
        return map;
    }

    @Override
    public Map<String, Object> toRefJson() {
        return null;
    }
}
