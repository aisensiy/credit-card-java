package com.tw.api;

import com.tw.api.exception.NotFoundException;
import com.tw.domain.*;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Application;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class ApiTestBase extends JerseyTest {
    @Mock
    protected InstalmentService instalmentService;
    @Mock
    ConsumerRepository consumerRepository;
    @Mock
    PaymentRequestRepository paymentRequestRepository;
    @Mock
    InstalmentRequestRepository instalmentRequestRepository;
    @Mock
    BillRepository billRepository;
    @Mock
    BillCreationService billCreationService;
    @Mock
    InstalmentPolicyRepository instalmentPolicyRepository;

    @Override
    protected Application configure() {
        return new ResourceConfig().packages("com.tw.api")
                .register(NotFoundException.class)
                .register(
                        new AbstractBinder() {
                            @Override
                            protected void configure() {
                                bind(consumerRepository).to(ConsumerRepository.class);
                                bind(paymentRequestRepository).to(PaymentRequestRepository.class);
                                bind(instalmentRequestRepository).to(InstalmentRequestRepository.class);
                                bind(billRepository).to(BillRepository.class);
                                bind(billCreationService).to(BillCreationService.class);
                                bind(instalmentPolicyRepository).to(InstalmentPolicyRepository.class);
                                bind(instalmentService).to(InstalmentService.class);
                            }
                        }
                );
    }
}
