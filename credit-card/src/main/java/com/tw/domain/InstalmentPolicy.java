package com.tw.domain;

import java.util.Map;

public class InstalmentPolicy implements Record {
    int id;
    private int term;
    private int commission;

    public InstalmentPolicy(int term, int commission) {
        this.term = term;
        this.commission = commission;
    }

    public int getId() {
        return id;
    }

    public int getTerm() {
        return term;
    }

    public int getCommission() {
        return commission;
    }

    public InstalmentPolicy() {
    }

    @Override
    public Map<String, Object> toJson() {
        return null;
    }

    @Override
    public Map<String, Object> toRefJson() {
        return null;
    }
}
