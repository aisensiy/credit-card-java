package com.tw.api;

import com.tw.domain.*;
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

    @Test
    public void should_create_instalment_request() throws Exception {
        Consumer consumer = TestHelper.consumer(1, "name");
        when(consumerRepository.findConsumerById(eq(1))).thenReturn(consumer);
        Bill bill = TestHelper.bill(1, 900, new Timestamp(1447040446924L), Date.valueOf("2015-11-09"), consumer);
        when(billRepository.findBillById(eq(1))).thenReturn(bill);
        final InstalmentPolicy policy = TestHelper.instalmentPolicy(1, new InstalmentPolicy(3, 1));
        when(instalmentPolicyRepository.findInstalmentPolicyById(eq(1))).thenReturn(policy);
        InstalmentRequest instalmentRequest = TestHelper.instalmentRequest(1, new InstalmentRequest(bill, policy));
        when(instalmentService.createInstalment(eq(bill), eq(policy))).thenReturn(instalmentRequest);

        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("policyId", "1");
        final Response response = target("/consumers/1/bills/1/instalmentRequests").request().post(Entity.form(new Form(map)));
        assertThat(response.getStatus(), is(201));
    }
}
