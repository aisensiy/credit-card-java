package com.tw.api;

import com.tw.domain.*;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class InstalmentApiTest extends ApiTestBase {

    private Consumer consumer;
    private Bill bill;
    private InstalmentPolicy policy;
    private InstalmentRequest instalmentRequest;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        consumer = TestHelper.consumer(1, "name");
        bill = TestHelper.bill(1, 900, new Timestamp(1447040446924L), Date.valueOf("2015-11-09"), consumer);
        policy = TestHelper.instalmentPolicy(1, new InstalmentPolicy(3, 1));
        instalmentRequest = TestHelper.instalmentRequest(1, new InstalmentRequest(bill, policy));
        when(consumerRepository.findConsumerById(eq(1))).thenReturn(consumer);
        when(billRepository.findBillById(eq(1))).thenReturn(bill);
        when(instalmentPolicyRepository.findInstalmentPolicyById(eq(1))).thenReturn(policy);
    }

    @Test
    public void should_create_instalment_request() throws Exception {

        when(instalmentService.createInstalment(eq(bill), eq(policy))).thenReturn(instalmentRequest);

        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("policyId", "1");
        final Response response = target("/consumers/1/bills/1/instalmentRequests").request().post(Entity.form(new Form(map)));
        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_confirm_instalment_request() throws Exception {
        when(instalmentRequestRepository.findInstalmentRequestById(eq(1))).thenReturn(instalmentRequest);
        final Response response = target("/consumers/1/bills/1/instalmentRequests/1/confirmation").request().post(Entity.form(new Form()));
        assertThat(response.getStatus(), is(204));
        assertThat(instalmentRequest.getStatus(), is(RequestStatus.CONFIRMED));
    }

    @Test
    public void should_reject_instalment_request() throws Exception {
        when(instalmentRequestRepository.findInstalmentRequestById(eq(1))).thenReturn(instalmentRequest);
        final Response response = target("/consumers/1/bills/1/instalmentRequests/1/rejected").request().post(Entity.form(new Form()));
        assertThat(response.getStatus(), is(204));
        assertThat(instalmentRequest.getStatus(), is(RequestStatus.REJECTED));
    }
}
