package com.tw.api;

import com.tw.domain.InstalmentPolicy;
import com.tw.domain.TestHelper;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class InstalmentPolicyApiTest extends ApiTestBase {
    @Test
    public void should_create_instalment_policy() throws Exception {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        InstalmentPolicy instalmentPolicy = TestHelper.instalmentPolicy(1, new InstalmentPolicy(6, 3));
        when(instalmentPolicyRepository.createInstalmentPolicy(eq(map))).thenReturn(instalmentPolicy);

        final Response response = target("/instalmentPolicies").request().post(Entity.form(new Form()));
        assertThat(response.getStatus(), is(201));
        assertThat(response.getHeaderString("Location").endsWith("/instalmentPolicies/1"), is(true));
    }
}
