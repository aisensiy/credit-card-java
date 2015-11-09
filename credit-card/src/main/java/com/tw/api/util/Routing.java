package com.tw.api.util;

import com.tw.domain.*;

import java.net.URI;

import static javax.ws.rs.core.UriBuilder.fromUri;

public class Routing {
    public static URI consumer(Consumer consumer) {
        return fromUri("/consumers/{consumerId}").build(consumer.getId());
    }

    public static URI paymentRequest(PaymentRequest paymentRequest) {
        return fromUri("/consumers/{consumerId}/paymentRequests/{paymentRequestId}")
                .build(paymentRequest.getConsumeBy().getId(), paymentRequest.getId());
    }

    public static URI bill(Bill bill) {
        return fromUri("/consumers/{consumerId}/bills/{billId}")
                .build(bill.getConsumer().getId(), bill.getId());
    }

    public static URI instalmentPolicy(InstalmentPolicy instalmentPolicy) {
        return fromUri("/instalmentPolicies/{id}").build(instalmentPolicy.getId());
    }

    public static URI instalmentRequest(InstalmentRequest instalmentRequest) {
        return fromUri("/consumers/{consumerId}/bills/{billId}")
                .build(instalmentRequest.getBill().getConsumer().getId(),
                        instalmentRequest.getBill().getId(),
                        instalmentRequest.getId());
    }
}
