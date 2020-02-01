package com.fflags.client;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

public class FFClient implements AutoCloseable {
    private final String sdkKey;
    private final FFlagsConfig config;

    public FFClient(String sdkKey) {
        checkNotNull(sdkKey, "Sdk key must not be null");
        config = new FFlagsConfig();
        checkNotNull(config, "Invalid configuration");
        isValid(sdkKey);
        this.sdkKey = sdkKey;
    }

    private boolean isValid(String sdkKey) {
        return false;
    }

    @Override
    public void close() throws IOException {
        System.out.println("Closing!");
    }
}
