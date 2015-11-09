package com.tw.domain;

import com.tw.mapper.ConsumerMapper;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class ConsumerRepositoryImplTest extends RepositoryTestBase {

    private ConsumerRepository consumerRepository;
    private MultivaluedMap<String, String> map;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        consumerRepository = new ConsumerRepositoryImpl(sqlSession.getMapper(ConsumerMapper.class));
        map = new MultivaluedHashMap<>();
        map.putSingle("billDay", "15");
        map.putSingle("repaymentDay", "3");
        map.putSingle("name", "name");
        map.putSingle("creditLine", "10000");
    }

    @Test
    public void createConsumer() throws Exception {
        final Consumer consumer = consumerRepository.createConsumer(map);
        assertThat(consumer.getId(), not(0));
        assertThat(consumer.getCreditLine(), is(10000));
    }

    @Test
    public void findConsumerById() throws Exception {
        final Consumer expected = consumerRepository.createConsumer(map);
        final Consumer consumer = consumerRepository.findConsumerById(expected.getId());
        assertThat(consumer.getId(), is(expected.getId()));
    }

    @Test
    public void updateConsumer() throws Exception {
        final Consumer consumer = consumerRepository.createConsumer(map);
        map.putSingle("name", "newName");
        final Consumer newConsumer = consumerRepository.updateConsumer(consumer.getId(), map);
        assertThat(newConsumer.getId(), is(consumer.getId()));
        assertThat(newConsumer.getName(), is("newName"));
    }
}