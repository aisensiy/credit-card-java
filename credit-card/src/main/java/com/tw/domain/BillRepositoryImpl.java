package com.tw.domain;

import com.tw.mapper.BillItemMapper;
import com.tw.mapper.BillMapper;

public class BillRepositoryImpl implements BillRepository {
    private final BillMapper billMapper;
    private final BillItemMapper billItemMapper;

    public BillRepositoryImpl(BillMapper billMapper, BillItemMapper billItemMapper) {

        this.billMapper = billMapper;
        this.billItemMapper = billItemMapper;
    }

    @Override
    public Bill createBill(Bill bill) {
        billMapper.createBill(bill);
        for (BillItem item : bill.getItems())
            billItemMapper.createBillItem(item);
        return findBillById(bill.getId());
    }

    @Override
    public Bill findBillById(int billId) {
        return billMapper.findBillById(billId);
    }
}
