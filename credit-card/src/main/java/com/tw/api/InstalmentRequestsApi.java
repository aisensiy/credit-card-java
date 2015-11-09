package com.tw.api;

import com.tw.api.exception.NotFoundException;
import com.tw.api.util.Routing;
import com.tw.domain.*;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
        final int amount = Integer.parseInt(form.asMap().getFirst("amount"));
        InstalmentPolicy instalmentPolicy = instalmentPolicyRepository.findInstalmentPolicyById(policyId);
        if (instalmentPolicy == null) {
            return Response.status(400).build();
        }
        InstalmentRequest instalmentRequest = instalmentService.createInstalment(amount, bill, instalmentPolicy);
        return Response.created(Routing.instalmentRequest(instalmentRequest)).build();
    }

    @Path("{instalmentRequestId}")
    public InstalmentRequestApi getInstalmentRequestApi(@PathParam("instalmentRequestId") int requestId,
                                                        @Context InstalmentRequestRepository instalmentRequestRepository) {
        InstalmentRequest instalmentRequest = instalmentRequestRepository.findInstalmentRequestById(requestId);
        if (instalmentRequest == null) {
            throw new NotFoundException();
        } else {
            return new InstalmentRequestApi(instalmentRequest);
        }
    }
}
