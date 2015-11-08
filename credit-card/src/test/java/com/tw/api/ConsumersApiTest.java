package com.tw.api;


import com.tw.api.util.Routing;
import com.tw.domain.Consumer;
import com.tw.domain.TestHelper;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class ConsumersApiTest extends ApiTestBase {
    @Test
    public void should_create_consumer() throws Exception {
        final Form form = new Form();
        Consumer consumer = TestHelper.consumer(1, "name");
        when(consumer.getId()).thenReturn(1);
        when(consumerRepository.createConsumer(eq(form.asMap()))).thenReturn(consumer);
        final Response response = target("/consumers").request().post(Entity.form(form));
        assertThat(response.getStatus(), is(201));
        assertThat(response.getHeaderString("Location"), endsWith(Routing.consumer(consumer).toString()));
    }

    @Test
    public void should_return_400_if_repository_failed_to_create_consumer() throws Exception {
        final Form form = new Form();
        when(consumerRepository.createConsumer(eq(form.asMap()))).thenReturn(null);
        final Response response = target("/consumers").request().post(Entity.form(form));
        assertThat(response.getStatus(), is(400));
    }

    @Test
    public void should_get_consumer_info() throws Exception {
        Consumer consumer = TestHelper.consumer(1, "abc");

        when(consumerRepository.findConsumerById(eq(1))).thenReturn(consumer);

        final Response response = target("/consumers/1").request().get();
        assertThat(response.getStatus(), is(200));
        Map map = response.readEntity(Map.class);
        assertThat(map.get("id"), is(1));
        assertThat(map.get("repaymentDay"), is(consumer.getRepaymentDay()));
        assertThat(map.get("billDay"), is(consumer.getBillDay()));
        assertThat(map.get("creditLine"), is(consumer.getCreditLine()));
        assertThat(map.get("name"), is(consumer.getName()));
    }
}
