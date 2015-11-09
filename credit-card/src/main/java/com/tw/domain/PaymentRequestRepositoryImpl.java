package com.tw.domain;

import com.tw.mapper.PaymentRequestMapper;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class PaymentRequestRepositoryImpl implements PaymentRequestRepository {
    private PaymentRequestMapper requestMapper;

    public PaymentRequestRepositoryImpl(PaymentRequestMapper requestMapper) {

        this.requestMapper = requestMapper;
    }

    @Override
    public PaymentRequest createPaymentRequest(Consumer consumer, MultivaluedMap<String, String> map) {
        PaymentRequest paymentRequest = new PaymentRequest(
                Integer.parseInt(map.getFirst("amount")),
                new Timestamp(new java.util.Date().getTime()),
                consumer
        );
        requestMapper.createPaymentRequest(paymentRequest);
        return requestMapper.findPaymentRequestById(paymentRequest.getId());
    }

    @Override
    public PaymentRequest findPaymentRequestById(int paymentRequestId) {
        return requestMapper.findPaymentRequestById(paymentRequestId);
    }

    @Override
    public PaymentRequest updatePaymentRequest(PaymentRequest paymentRequest) {
        requestMapper.updatePaymentRequest(paymentRequest);
        return paymentRequest;
    }

    @Override
    public List<PaymentRequest> findConfirmedPaymentRequestsByDateRange(Date from, Date to, Consumer consumer) {
        return requestMapper.findRequestsByRange(from, to, consumer, RequestStatus.CONFIRMED);
    }
}
