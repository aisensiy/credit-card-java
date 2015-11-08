package com.tw.api.util;

import com.tw.domain.Consumer;

import java.net.URI;

import static javax.ws.rs.core.UriBuilder.fromUri;

public class Routing {
    public static URI consumer(Consumer consumer) {
        return fromUri("/consumers/{consumerId}").build(consumer.getId());
    }
}
