package com.tw.api;

import com.tw.domain.Bill;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BillApi {
    private Bill bill;

    public BillApi(Bill bill) {

        this.bill = bill;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBill() {
        return Response.ok().entity(bill.toJson()).build();
    }

    @Path("instalmentRequests")
    public InstalmentRequestsApi getInstalmentRequestsApi() {
        return new InstalmentRequestsApi(bill);
    }
}
