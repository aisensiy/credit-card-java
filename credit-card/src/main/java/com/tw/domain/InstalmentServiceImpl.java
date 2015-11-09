package com.tw.domain;

public class InstalmentServiceImpl implements InstalmentService {
    @Override
    public InstalmentRequest createInstalment(int amount, Bill bill, InstalmentPolicy policy) {
        InstalmentRequest instalmentRequest = new InstalmentRequest(amount, bill, policy);
        instalmentRequest.createInstalmentItems();
        return instalmentRequest;
    }
}
