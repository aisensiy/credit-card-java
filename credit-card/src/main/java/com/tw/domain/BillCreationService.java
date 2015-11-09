package com.tw.domain;

import java.sql.Timestamp;

public interface BillCreationService {
    Bill createBill(Consumer consumer, Timestamp createdAt);
}
