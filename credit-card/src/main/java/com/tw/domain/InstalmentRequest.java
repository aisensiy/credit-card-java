package com.tw.domain;

public class InstalmentRequest implements Request {
    int id;
    private Bill bill;
    private final InstalmentPolicy policy;
    private RequestStatus status = RequestStatus.NEW;

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
}
