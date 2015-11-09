package com.tw.domain;

public interface InstalmentService {
    InstalmentRequest createInstalment(Bill bill, InstalmentPolicy policy);
}
