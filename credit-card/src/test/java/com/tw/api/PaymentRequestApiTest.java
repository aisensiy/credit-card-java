package com.tw.api;

import com.tw.domain.Consumer;
import com.tw.domain.PaymentRequest;
import com.tw.domain.PaymentStatus;
import com.tw.domain.TestHelper;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
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

    @Test
    public void should_get_payment_request() throws Exception {
        final Consumer consumer = TestHelper.consumer(1, "name");
        PaymentRequest paymentRequest = TestHelper.paymentRequest(1, 100, consumer);
        when(paymentRequestRepository.findPaymentRequestById(eq(1))).thenReturn(paymentRequest);
        when(consumerRepository.findConsumerById(eq(1))).thenReturn(consumer);

        final Response response = target("/consumers/1/paymentRequests/1").request().get();
        assertThat(response.getStatus(), is(200));
        Map map = response.readEntity(Map.class);
        assertThat(map.get("id"), is(1));
        assertThat(map.get("amount"), is(100));
        assertThat(map.get("status"), is("NEW"));
        Map consumeby = (Map) map.get("consumer");
        assertThat((String) consumeby.get("uri"), endsWith("/consumers/1"));
    }

    @Test
    public void should_confirm_request() throws Exception {
        final Consumer consumer = TestHelper.consumer(1, "name");
        PaymentRequest paymentRequest = TestHelper.paymentRequest(1, 100, consumer);
        when(paymentRequestRepository.findPaymentRequestById(eq(1))).thenReturn(paymentRequest);
        when(consumerRepository.findConsumerById(eq(1))).thenReturn(consumer);
        when(paymentRequestRepository.updatePaymentRequest(paymentRequest)).thenReturn(paymentRequest);

        final Response response = target("/consumers/1/paymentRequests/1/confirmation").request().post(Entity.form(new Form()));
        assertThat(response.getStatus(), is(204));
        assertThat(paymentRequest.getStatus(), is(PaymentStatus.CONFIRMED));
        verify(paymentRequestRepository).updatePaymentRequest(eq(paymentRequest));
    }

    @Test
    public void should_reject_request() throws Exception {
        final Consumer consumer = TestHelper.consumer(1, "name");
        PaymentRequest paymentRequest = TestHelper.paymentRequest(1, 100, consumer);
        when(paymentRequestRepository.findPaymentRequestById(eq(1))).thenReturn(paymentRequest);
        when(consumerRepository.findConsumerById(eq(1))).thenReturn(consumer);
        when(paymentRequestRepository.updatePaymentRequest(paymentRequest)).thenReturn(paymentRequest);

        final Response response = target("/consumers/1/paymentRequests/1/rejected").request().post(Entity.form(new Form()));
        assertThat(response.getStatus(), is(204));
        assertThat(paymentRequest.getStatus(), is(PaymentStatus.REJECTED));
        verify(paymentRequestRepository).updatePaymentRequest(eq(paymentRequest));
    }
}
