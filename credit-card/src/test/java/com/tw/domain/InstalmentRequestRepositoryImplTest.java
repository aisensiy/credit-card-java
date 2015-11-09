package com.tw.domain;

import com.tw.mapper.InstalmentItemMapper;
import com.tw.mapper.InstalmentRequestMapper;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class InstalmentRequestRepositoryImplTest extends RepositoryTestBase {

    private InstalmentRequestRepository instalmentRequestRepository;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        final InstalmentItemMapper instalmentItemMapper = sqlSession.getMapper(InstalmentItemMapper.class);
        final InstalmentRequestMapper instalmentRequestMapper = sqlSession.getMapper(InstalmentRequestMapper.class);
        instalmentRequestRepository = new InstalmentRequestRepositoryImpl(instalmentRequestMapper, instalmentItemMapper);
    }

    @Test
    public void should_create_get_and_update_instalment_request() throws Exception {
        Consumer consumer = TestHelper.consumer(1, "name");
        Bill bill = TestHelper.bill(1, 1000, new Timestamp(new java.util.Date().getTime()), consumer);
        InstalmentPolicy policy = TestHelper.instalmentPolicy(1, new InstalmentPolicy(3, 1));
        InstalmentRequest instalmentRequest = new InstalmentRequest(900, bill, policy);
        instalmentRequest.createInstalmentItems();
        instalmentRequestRepository.createInstalmentRequest(instalmentRequest);

        assertThat(instalmentRequest.getItems().get(0).getId(), not(0));
        instalmentRequest.reject();
        instalmentRequestRepository.updateInstalmentRequest(instalmentRequest);
        instalmentRequest = instalmentRequestRepository.findInstalmentRequestById(instalmentRequest.getId());
        assertThat(instalmentRequest.getStatus(), is(RequestStatus.REJECTED));
    }


}