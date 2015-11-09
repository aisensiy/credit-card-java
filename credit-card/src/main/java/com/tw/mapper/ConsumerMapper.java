package com.tw.mapper;

import com.tw.domain.Consumer;
import org.apache.ibatis.annotations.Param;

public interface ConsumerMapper {

    int createConsumer(@Param("consumer") Consumer consumer);

    Consumer findConsumerById(@Param("consumerId") int consumerId);

    int updateConsumer(@Param("consumer") Consumer consumer);
}
