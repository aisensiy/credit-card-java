package com.tw.domain;

import org.junit.Test;

import java.sql.Date;
import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InstalmentServiceImplTest {
    @Test
    public void should_create_stalmentItems() throws Exception {
        final InstalmentServiceImpl instalmentService = new InstalmentServiceImpl();
        Consumer consumer = TestHelper.consumer(1, "name");
        Bill bill = TestHelper.bill(1, 900, new Timestamp(1447048827122L), Date.valueOf("2015-11-15"), consumer);
        InstalmentPolicy policy = new InstalmentPolicy(3, 1);
        final InstalmentRequest instalment = instalmentService.createInstalment(900, bill, policy);
        assertThat(instalment.getItems().size(), is(policy.getTerm()));
        final int amountPerTerm = bill.getAmount() / policy.getTerm();
        final InstalmentItem item1 = instalment.getItems().get(0);
        final InstalmentItem item2 = instalment.getItems().get(1);
        final InstalmentItem item3 = instalment.getItems().get(2);
        assertThat(item1.getCommission(), is((int) (policy.getCommission() * 0.01 * amountPerTerm)));
        assertThat(item1.getRepaymentDay(), is(bill.getRepaymentDate()));
        assertThat(item2.getRepaymentDay().toString(), is(Date.valueOf("2016-01-03").toString()));
        assertThat(item3.getRepaymentDay().toString(), is(Date.valueOf("2016-02-03").toString()));
    }
}