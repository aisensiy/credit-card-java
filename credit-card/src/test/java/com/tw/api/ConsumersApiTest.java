package com.tw.api;


import com.tw.api.util.Routing;
import com.tw.domain.Consumer;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsumersApiTest extends ApiTestBase {
    @Test
    public void should_create_consumer() throws Exception {
        final Form form = new Form();
        Consumer consumer = mock(Consumer.class);
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
}
