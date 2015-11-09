package com.tw.api;

import com.tw.domain.Bill;
import com.tw.domain.Consumer;
import com.tw.domain.TestHelper;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class BillApiTest extends ApiTestBase {
    @Test
    public void should_create_new_bill() throws Exception {
        final Consumer consumer = TestHelper.consumer(1, "name");
        when(consumerRepository.findConsumerById(eq(1))).thenReturn(consumer);
        final Bill bill = TestHelper.bill(1, 1000, new Timestamp(1447038403863L), Date.valueOf("2015-11-09"), consumer);
        when(billCreationService.createBill(eq(consumer))).thenReturn(bill);
        when(billRepository.createBill(eq(bill))).thenReturn(bill);

        final Response response = target("/consumers/1/bills").request().post(Entity.form(new Form()));
        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_fail_to_create_new_bill() throws Exception {
        final Consumer consumer = TestHelper.consumer(1, "name");
        when(consumerRepository.findConsumerById(eq(1))).thenReturn(consumer);
        when(billCreationService.createBill(eq(consumer))).thenReturn(null);

        final Response response = target("/consumers/1/bills").request().post(Entity.form(new Form()));
        assertThat(response.getStatus(), is(400));
    }
}
