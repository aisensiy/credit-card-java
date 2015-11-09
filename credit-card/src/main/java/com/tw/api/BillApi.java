package com.tw.api;

import com.tw.domain.Bill;

import javax.ws.rs.Path;

public class BillApi {
    private Bill bill;

    public BillApi(Bill bill) {

        this.bill = bill;
    }

    @Path("instalmentRequests")
    public InstalmentRequestsApi getInstalmentRequestsApi() {
        return new InstalmentRequestsApi(bill);
    }
}
