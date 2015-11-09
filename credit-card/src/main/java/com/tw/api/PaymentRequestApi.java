package com.tw.api;

import com.tw.domain.PaymentRequest;
import com.tw.domain.PaymentRequestRepository;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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

    @POST
    @Path("/confirmation")
    public Response confirmed(@Context PaymentRequestRepository paymentRequestRepository) {
        paymentRequest.confirm();
        paymentRequest = paymentRequestRepository.updatePaymentRequest(paymentRequest);
        return Response.noContent().build();
    }

    @POST
    @Path("/rejected")
    public Response rejected(@Context PaymentRequestRepository paymentRequestRepository) {
        paymentRequest.reject();
        paymentRequest = paymentRequestRepository.updatePaymentRequest(paymentRequest);
        return Response.noContent().build();
    }
}
