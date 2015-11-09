package com.tw.domain;

import com.tw.api.util.Routing;

import java.util.HashMap;
import java.util.Map;

public class Consumer implements Record {
    int id;
    private int billDay;
    private int repaymentDay;
    private String name;
    private int creditLine;

    public Consumer(String name, int billDay, int repaymentDay, int creditLine) {
        this.billDay = billDay;
        this.repaymentDay = repaymentDay;
        this.name = name;
        this.creditLine = creditLine;
    }

    public int getId() {
        return id;
    }

    public int getBillDay() {
        return billDay;
    }

    public int getRepaymentDay() {
        return repaymentDay;
    }

    public String getName() {
        return name;
    }

    public int getCreditLine() {
        return creditLine;
    }

    @Override
    public Map<String, Object> toJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("billDay", billDay);
        map.put("repaymentDay", repaymentDay);
        map.put("creditLine", creditLine);
        map.put("id", id);
        map.put("name", name);
        return map;
    }

    @Override
    public Map<String, Object> toRefJson() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("uri", Routing.consumer(this));
        return map;
    }
}
