package com.tw.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static com.tw.domain.DateUtil.*;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(MockitoJUnitRunner.class)
public class BillCreationServiceImplTest {
    @Mock
    PaymentRequestRepository paymentRequestRepository;

    @Mock
    InstalmentRequestRepository instalmentRequestRepository;

    @InjectMocks
    BillCreationServiceImpl billCreationService = new BillCreationServiceImpl();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void should_create_new_bill() throws Exception {
        Consumer consumer = TestHelper.consumer(1, "name");
        final Timestamp today = new Timestamp(1447570035904L); // 2015-11-15
        Bill expected = new Bill(today, consumer);
        Date from = addDay(1, addMonth(-1, new Date(today.getTime())));
        Date to = expected.getBillDate();
        final List<PaymentRequest> paymentRequests = asList(
                TestHelper.paymentRequest(1, 100, consumer),
                TestHelper.paymentRequest(2, 100, consumer)
        );
        when(paymentRequestRepository.findConfirmedPaymentRequestsByDateRange(eq(from), eq(to))).thenReturn(paymentRequests);

        List<InstalmentItem> instalmentItems = asList(
                new InstalmentItem(100, 3, expected.getRepaymentDate())
        );
        when(instalmentRequestRepository.findConfirmedInstalmentsByRepaymentDay(eq(expected.getRepaymentDate()))).thenReturn(instalmentItems);

        final Bill bill = billCreationService.createBill(consumer, today);

        assertThat(bill.getBillDate(), is(expected.getBillDate()));
        assertThat(bill.getAmount(), is(303));
        assertThat(bill.getItems().size(), is(3));
    }
}