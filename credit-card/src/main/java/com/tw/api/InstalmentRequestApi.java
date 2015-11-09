package com.tw.api;

import com.tw.domain.InstalmentRequest;
import com.tw.domain.InstalmentRequestRepository;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

public class InstalmentRequestApi {
    private InstalmentRequest instalmentRequest;

    public InstalmentRequestApi(InstalmentRequest instalmentRequest) {

        this.instalmentRequest = instalmentRequest;
    }

    @POST
    @Path("confirmation")
    public Response confired(@Context InstalmentRequestRepository instalmentRequestRepository) {
        instalmentRequest.confirm();
        instalmentRequestRepository.updateInstalmentRequest(instalmentRequest);
        return Response.noContent().build();
    }

    @POST
    @Path("rejected")
    public Response rejected(@Context InstalmentRequestRepository instalmentRequestRepository) {
        instalmentRequest.reject();
        instalmentRequestRepository.updateInstalmentRequest(instalmentRequest);
        return Response.noContent().build();
    }
}
