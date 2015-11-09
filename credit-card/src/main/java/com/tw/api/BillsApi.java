package com.tw.api;

import com.tw.api.exception.NotFoundException;
import com.tw.api.util.Routing;
import com.tw.domain.Bill;
import com.tw.domain.BillCreationService;
import com.tw.domain.Consumer;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @Path("{billId}")
    public BillApi getBillApi(@PathParam("billId") int billId,
                              @Context BillRepository billRepository) {
        Bill bill = billRepository.findBillById(billId);
        if (bill == null) {
            throw new NotFoundException();
        } else {
            return new BillApi(bill);
        }
    }
}
