package com.tw.api;

import com.tw.domain.Consumer;
import com.tw.domain.PaymentRequest;
import com.tw.domain.TestHelper;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyObject;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class PaymentRequestApiTest extends ApiTestBase {
    @Test
    public void should_create_payment_request() throws Exception {
        final Consumer consumer = TestHelper.consumer(1, "name");
        PaymentRequest paymentRequest = TestHelper.paymentRequest(1, 100, consumer);
        when(paymentRequestRepository.createPaymentRequest(eq(consumer), anyObject())).thenReturn(paymentRequest);
        when(consumerRepository.findConsumerById(eq(1))).thenReturn(consumer);
        final Response response = target("/consumers/1/paymentRequests").request().post(Entity.form(new Form()));
        assertThat(response.getStatus(), is(201));
        assertThat(response.getHeaderString("Location"), endsWith("/consumers/1/paymentRequests/1"));
    }
}
