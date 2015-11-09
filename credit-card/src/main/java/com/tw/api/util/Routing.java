package com.tw.api.util;

import com.tw.domain.Consumer;
import com.tw.domain.PaymentRequest;

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
}
