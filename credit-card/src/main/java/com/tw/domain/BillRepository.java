package com.tw.domain;

public interface BillRepository {
    Bill createBill(Bill bill);

    Bill findBillById(int billId);
}
