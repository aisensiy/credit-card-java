package com.tw.domain;

import javax.ws.rs.core.MultivaluedMap;

public interface PaymentRequestRepository {
    PaymentRequest createPaymentRequest(Consumer consumer, MultivaluedMap<String, String> map);

    PaymentRequest findPaymentRequestById(int paymentRequestId);
}
