package com.fflags.client;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

public class FFClient implements AutoCloseable {
    private final String sdkKey;

    public FFClient(String sdkKey) {
        checkNotNull(sdkKey, "Sdk key must not be null");
        this.sdkKey = sdkKey;
    }

    @Override
    public void close() throws IOException {
        System.out.println("Closing!");
    }
}
