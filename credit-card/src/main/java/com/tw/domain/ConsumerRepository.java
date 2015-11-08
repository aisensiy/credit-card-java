package com.tw.domain;

import javax.ws.rs.core.MultivaluedMap;

public interface ConsumerRepository {
    Consumer createConsumer(MultivaluedMap<String, String> map);
}
