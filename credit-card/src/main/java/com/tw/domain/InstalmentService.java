package com.tw.domain;

public interface InstalmentService {
    InstalmentRequest createInstalment(int amount, Bill bill, InstalmentPolicy policy);
}
