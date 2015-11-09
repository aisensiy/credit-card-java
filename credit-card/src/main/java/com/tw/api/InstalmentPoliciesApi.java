package com.tw.api;

import com.tw.api.util.Routing;
import com.tw.domain.InstalmentPolicy;
import com.tw.domain.InstalmentPolicyRepository;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

@Path("/instalmentPolicies")
public class InstalmentPoliciesApi {

    @POST
    public Response createPolicy(Form form, @Context InstalmentPolicyRepository instalmentPolicyRepository) {
        InstalmentPolicy instalmentPolicy = instalmentPolicyRepository.createInstalmentPolicy(form.asMap());
        if (instalmentPolicy != null) {
            return Response.created(Routing.instalmentPolicy(instalmentPolicy)).build();
        } else {
            return Response.status(400).build();
        }
    }
}
