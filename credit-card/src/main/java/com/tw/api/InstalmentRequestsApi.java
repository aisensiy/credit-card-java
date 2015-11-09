package com.tw.api;

import com.tw.api.util.Routing;
import com.tw.domain.*;

import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

public class InstalmentRequestsApi {
    private Bill bill;

    public InstalmentRequestsApi(Bill bill) {

        this.bill = bill;
    }

    @POST
    public Response createRequest(Form form,
                                  @Context InstalmentPolicyRepository instalmentPolicyRepository,
                                  @Context InstalmentService instalmentService) {
        final int policyId = Integer.parseInt(form.asMap().getFirst("policyId"));
        InstalmentPolicy instalmentPolicy = instalmentPolicyRepository.findInstalmentPolicyById(policyId);
        if (instalmentPolicy == null) {
            return Response.status(400).build();
        }
        InstalmentRequest instalmentRequest = instalmentService.createInstalment(bill, instalmentPolicy);
        return Response.created(Routing.instalmentRequest(instalmentRequest)).build();
    }
}
