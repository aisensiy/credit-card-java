package com.tw.api;

import com.tw.api.util.Routing;
import com.tw.domain.*;

import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

public class BillsApi {
    private Consumer consumer;

    public BillsApi(Consumer consumer) {

        this.consumer = consumer;
    }

    @POST
    public Response createBill(@Context BillCreationService billCreationService,
                               @Context BillRepository billRepository) {
        Bill bill = billCreationService.createBill(consumer);
        if (bill == null) {
            return Response.status(400).build();
        }
        bill = billRepository.createBill(bill);
        return Response.created(Routing.bill(bill)).build();
    }
}
