package com.university.booking.enums;

import lombok.Getter;

@Getter
public enum RedisKeyPrefix {
    BOOKING("booking:"),
    CATEGORY("category:"),
    PERSON("person:"),
    ROOM("room:"),
    CART("cart:"),
    RATE_LIMIT("rate:limit:");

    private final String prefix;

    RedisKeyPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getKey(String id) {
        return this.prefix + id;
    }
}
