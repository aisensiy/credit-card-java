package com.tw.domain;

import com.tw.mapper.ConsumerMapper;

import javax.ws.rs.core.MultivaluedMap;

public class ConsumerRepositoryImpl implements ConsumerRepository {
    private ConsumerMapper mapper;

    public ConsumerRepositoryImpl(ConsumerMapper mapper) {

        this.mapper = mapper;
    }

    @Override
    public Consumer createConsumer(MultivaluedMap<String, String> map) {
        Consumer consumer = new Consumer(
                map.getFirst("name"),
                Integer.parseInt(map.getFirst("billDay")),
                Integer.parseInt(map.getFirst("repaymentDay")),
                Integer.parseInt(map.getFirst("creditLine"))
        );
        mapper.createConsumer(consumer);
        return consumer;
    }

    @Override
    public Consumer findConsumerById(int consumerId) {
        return mapper.findConsumerById(consumerId);
    }

    @Override
    public Consumer updateConsumer(int consumerId, MultivaluedMap<String, String> map) {
        Consumer consumer = new Consumer(
                map.getFirst("name"),
                Integer.parseInt(map.getFirst("billDay")),
                Integer.parseInt(map.getFirst("repaymentDay")),
                Integer.parseInt(map.getFirst("creditLine"))
        );
        consumer.id = consumerId;
        mapper.updateConsumer(consumer);
        return findConsumerById(consumerId);
    }
}
