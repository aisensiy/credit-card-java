package com.tw.domain;

public class InstalmentRequest {
    int id;
    private Bill bill;
    private final InstalmentPolicy policy;

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
}
