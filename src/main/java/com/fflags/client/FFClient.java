package com.fflags.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static com.google.common.base.Preconditions.checkNotNull;

public class FFClient implements AutoCloseable {
    private final FFlagsConfig config;
    private FFHttpClient httpClient;

    public FFClient(String sdkKey) {
        checkNotNull(sdkKey, "Sdk key must not be null");
        this.config = new FFlagsConfig(sdkKey);
        this.httpClient = new FFHttpClient();
    }

    public void init() {
        try {
            URL initURL = this.config.init();
            httpClient.post(initURL);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        System.out.println("Closing!");
    }
}
