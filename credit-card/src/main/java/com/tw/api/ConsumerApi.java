package com.tw.api;

import com.tw.domain.Consumer;
import com.tw.domain.ConsumerRepository;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ConsumerApi {
    private Consumer consumer;

    public ConsumerApi(Consumer consumer) {

        this.consumer = consumer;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConsumer() {
        return Response.ok().entity(consumer.toJson()).build();
    }

    @PUT
    public Response updateConsumer(Form form,
                                   @Context ConsumerRepository consumerRepository) {
        consumerRepository.updateConsumer(consumer.getId(), form.asMap());
        return Response.noContent().build();
    }

    @Path("paymentRequests")
    public PaymentRequestsApi getPaymentRequestsApi() {
        return new PaymentRequestsApi(consumer);
    }
}
