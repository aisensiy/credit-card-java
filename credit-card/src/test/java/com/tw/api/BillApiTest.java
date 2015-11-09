package com.tw.api;

import com.tw.domain.Bill;
import com.tw.domain.Consumer;
import com.tw.domain.TestHelper;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class BillApiTest extends ApiTestBase {

    private Consumer consumer;
    private Bill bill;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        consumer = TestHelper.consumer(1, "name");
        when(consumerRepository.findConsumerById(eq(1))).thenReturn(consumer);
        bill = TestHelper.billWithItems(1, 1000, new Timestamp(1447038403863L), Date.valueOf("2015-11-09"), consumer);
    }

    @Test
    public void should_create_new_bill() throws Exception {
        when(billCreationService.createBill(eq(consumer))).thenReturn(bill);
        when(billRepository.createBill(eq(bill))).thenReturn(bill);

        final Response response = target("/consumers/1/bills").request().post(Entity.form(new Form()));
        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_get_bill() throws Exception {
        when(billRepository.findBillById(eq(1))).thenReturn(bill);
        final Response response = target("/consumers/1/bills/1").request().get();
        assertThat(response.getStatus(), is(200));
        Map map = response.readEntity(Map.class);
        assertThat(map.get("amount"), is(1000));
        List list = (List) map.get("items");
        assertThat(list.size(), is(2));
        Map item1 = (Map) list.get(0);
        assertThat(item1.get("amount"), is(500));
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
