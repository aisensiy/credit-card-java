package com.tw.domain;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.Date;
import java.util.List;

public interface PaymentRequestRepository {
    PaymentRequest createPaymentRequest(Consumer consumer, MultivaluedMap<String, String> map);

    PaymentRequest findPaymentRequestById(int paymentRequestId);

    PaymentRequest updatePaymentRequest(PaymentRequest paymentRequest);

    List<PaymentRequest> findConfirmedPaymentRequestsByDateRange(Date from, Date to, Consumer consumer);
}
