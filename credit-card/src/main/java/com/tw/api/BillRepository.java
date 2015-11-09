package com.tw.api;

import com.tw.domain.Bill;

public interface BillRepository {
    Bill createBill(Bill bill);
}
