package com.tw.api;

import com.tw.domain.PaymentRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PaymentRequestApi {
    private PaymentRequest paymentRequest;

    public PaymentRequestApi(PaymentRequest paymentRequest) {

        this.paymentRequest = paymentRequest;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentRequest() {
        return Response.ok().entity(paymentRequest.toJson()).build();
    }
}
