package com.tw.api;

import com.tw.api.util.Routing;
import com.tw.domain.Consumer;
import com.tw.domain.ConsumerRepository;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

@Path("consumers")
public class ConsumersApi {
    @POST
    public Response createConsumer(Form form,
                                   @Context ConsumerRepository consumerRepository) {
        Consumer consumer = consumerRepository.createConsumer(form.asMap());
        if (consumer != null) {
            return Response.created(Routing.consumer(consumer)).build();
        } else {
            return Response.status(400).build();
        }
    }
}
