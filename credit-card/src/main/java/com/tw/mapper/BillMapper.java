package com.tw.mapper;

import com.tw.domain.Bill;
import org.apache.ibatis.annotations.Param;

public interface BillMapper {
    int createBill(@Param("bill") Bill bill);

    Bill findBillById(@Param("id") int billId);
}
