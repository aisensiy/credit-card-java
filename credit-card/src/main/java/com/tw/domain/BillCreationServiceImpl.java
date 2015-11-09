package com.tw.domain;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.tw.domain.DateUtil.addDay;
import static com.tw.domain.DateUtil.addMonth;

public class BillCreationServiceImpl implements BillCreationService {
    @Inject
    PaymentRequestRepository paymentRequestRepository;

    @Inject
    InstalmentRequestRepository instalmentRequestRepository;

    @Override
    public Bill createBill(Consumer consumer, Timestamp createdAt) {
        Bill bill = new Bill(createdAt, consumer);
        Date from = addDay(1, addMonth(-1, new Date(bill.getBillDay().getTime())));
        Date to = bill.getBillDay();
        final List<PaymentRequest> payments = paymentRequestRepository.findConfirmedPaymentRequestsByDateRange(from, to, consumer);
        final List<InstalmentItem> instalments = instalmentRequestRepository.findConfirmedInstalmentsByRepaymentDay(bill.getRepaymentDay(), consumer);
        List<BillItem> billItems = new ArrayList<>();
        for (PaymentRequest paymentRequest : payments) {
            billItems.add(new BillItem(paymentRequest.getAmount(), ItemType.PAYMENT, bill));
        }
        for (InstalmentItem instalmentItem : instalments) {
            billItems.add(new BillItem(instalmentItem.getAmount() + instalmentItem.getCommission(), ItemType.INSTALMENT, bill));
        }
        bill.setBillItems(billItems);
        return bill;
    }
}
