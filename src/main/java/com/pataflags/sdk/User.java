package com.pataflags.sdk;

import java.util.Map;

public class User {
    private final String identity;
    private final Map<String, Value<?>> attributes;

    public User(String identity, Map<String, Value<?>> attributes) {
        this.identity = identity;
        this.attributes = attributes;
    }

    String getIdentity() {
        return identity;
    }

    Value getValue(String attribute) {
        return attributes.get(attribute);
    }
}
