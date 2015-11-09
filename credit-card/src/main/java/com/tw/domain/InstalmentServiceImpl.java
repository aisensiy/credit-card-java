package com.tw.domain;

import javax.inject.Inject;

public class InstalmentServiceImpl implements InstalmentService {
    @Inject
    InstalmentRequestRepository instalmentRequestRepository;

    @Override
    public InstalmentRequest createInstalment(int amount, Bill bill, InstalmentPolicy policy) {
        InstalmentRequest instalmentRequest = new InstalmentRequest(amount, bill, policy);
        instalmentRequest.createInstalmentItems();
        instalmentRequestRepository.createInstalmentRequest(instalmentRequest);
        return instalmentRequest;
    }
}
