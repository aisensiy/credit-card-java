package com.tw.api;

import com.tw.domain.Consumer;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
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
}
