package com.pataflags.sdk;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class PFClient implements AutoCloseable {
    private final FFlagsConfig config;
    private FFHttpClient httpClient;
    private List<Feature> features;

    public PFClient(String apiKey) {
        this(apiKey, new FFHttpClient());
    }

    PFClient(String apiKey, FFHttpClient httpClient) {
        checkNotNull(apiKey, "Sdk key must not be null");
        this.config = new FFlagsConfig(apiKey);
        this.httpClient = httpClient;
    }

    public void init() {
        try {
            URL initURL = this.config.init();
            this.features = httpClient.post(initURL);

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

    public Map<String, Boolean> evaluate(User user) {
        HashMap<String, Boolean> evaluation = new HashMap<>();

        for (Feature feature: features)
            evaluation.put(feature.key, feature.evaluate(user));

        return evaluation;
    }
}