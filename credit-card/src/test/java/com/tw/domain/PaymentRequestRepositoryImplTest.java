package com.tw.domain;

import com.tw.mapper.PaymentRequestMapper;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class PaymentRequestRepositoryImplTest extends RepositoryTestBase {

    private PaymentRequestRepository paymentRequestRepository;
    private PaymentRequestMapper requestMapper;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        requestMapper = sqlSession.getMapper(PaymentRequestMapper.class);
        paymentRequestRepository = new PaymentRequestRepositoryImpl(requestMapper);
    }

    @Test
    public void should_create_and_get_and_update_payment_request() throws Exception {
        MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
        map.putSingle("amount", "100");
        Consumer consumer = TestHelper.consumer(1, "name");
        PaymentRequest paymentRequest = paymentRequestRepository.createPaymentRequest(consumer, map);
        assertThat(paymentRequest.getId(), not(0));
        assertThat(paymentRequest.getAmount(), is(100));
        assertThat(paymentRequest.getConsumeBy().getId(), is(1));
        assertThat(paymentRequest.getStatus(), is(RequestStatus.NEW));
        paymentRequest.reject();
        paymentRequest = paymentRequestRepository.updatePaymentRequest(paymentRequest);
        assertThat(paymentRequest.getStatus(), is(RequestStatus.REJECTED));
    }

    @Test
    public void should_list_payments_by_date_range() throws Exception {
        Consumer consumer = TestHelper.consumer(1, "name");
        final PaymentRequest paymentRequest = new PaymentRequest(100, new Timestamp(java.sql.Date.valueOf("2015-01-02").getTime()), consumer);
        requestMapper.createPaymentRequest(paymentRequest);
        requestMapper.createPaymentRequest(paymentRequest);
        paymentRequest.confirm();
        requestMapper.createPaymentRequest(paymentRequest);

        final java.sql.Date from = java.sql.Date.valueOf("2015-01-01");
        final java.sql.Date to = java.sql.Date.valueOf("2015-01-03");
        List<PaymentRequest> paymentList = paymentRequestRepository.findConfirmedPaymentRequestsByDateRange(from, to, consumer);
        assertThat(paymentList.size(), is(1));
    }
}