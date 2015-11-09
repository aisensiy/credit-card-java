package com.tw.domain;

import com.tw.mapper.BillItemMapper;
import com.tw.mapper.BillMapper;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

public class BillRepositoryImplTest extends RepositoryTestBase {

    private BillRepositoryImpl billRepository;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        final BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
        final BillItemMapper billItemMapper = sqlSession.getMapper(BillItemMapper.class);
        billRepository = new BillRepositoryImpl(billMapper, billItemMapper);
    }

    @Test
    public void should_create_ang_get_bill() throws Exception {
        Consumer consumer = TestHelper.consumer(1, "name");
        Bill bill = TestHelper.billWithItems(1, 1000, new Timestamp(1447038403863L), Date.valueOf("2015-11-09"), consumer);
        bill = billRepository.createBill(bill);
        assertThat(bill.getId(), not(0));
        assertThat(bill.getItems().get(0).getId(), not(0));
    }
}