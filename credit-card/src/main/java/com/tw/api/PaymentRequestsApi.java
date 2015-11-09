package com.tw.api;

import com.tw.api.exception.NotFoundException;
import com.tw.api.util.Routing;
import com.tw.domain.Consumer;
import com.tw.domain.PaymentRequest;
import com.tw.domain.PaymentRequestRepository;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class PaymentRequestsApi {
    private Consumer consumer;

    public PaymentRequestsApi(Consumer consumer) {

        this.consumer = consumer;
    }

    @POST
    public Response createPaymentRequest(Form form,
                                         @Context PaymentRequestRepository paymentRequestRepository) {
        PaymentRequest paymentRequest = paymentRequestRepository.createPaymentRequest(consumer, form.asMap());
        if (paymentRequest != null) {
            return Response.created(Routing.paymentRequest(paymentRequest)).build();
        } else {
            return Response.status(400).build();
        }
    }

    @Path("/{requestId}")
    public PaymentRequestApi getPaymentRequestApi(@PathParam("requestId") int requestId,
                                                  @Context PaymentRequestRepository paymentRequestRepository) {
        PaymentRequest paymentRequest = paymentRequestRepository.findPaymentRequestById(requestId);
        if (paymentRequest != null) {
            return new PaymentRequestApi(paymentRequest);
        } else {
            throw new NotFoundException();
        }

    }
}
