package com.pataflags.sdk;

import com.pataflags.evaluator.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static com.google.common.base.Preconditions.checkNotNull;

public class PFClient implements AutoCloseable {
    private final FFlagsConfig config;
    private String apiKey;
    private FFHttpClient httpClient;
    private PFStore store;

    public PFClient(String apiKey) {
        this(apiKey, new FFHttpClient());
    }

    PFClient(String apiKey, FFHttpClient httpClient) {
        checkNotNull(apiKey, "Sdk key must not be null");
        this.apiKey = apiKey;
        this.config = new FFlagsConfig(apiKey);
        this.httpClient = httpClient;
    }

    public void init() {
        try {
            URL initURL = this.config.init();
            ServerInitializeResponseDTO dto = httpClient.get(initURL, apiKey);
            this.store = new PFInMemoryStore(dto.features, dto.segments);
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

    public PFEvaluation evaluate(PFUser user) {
        return new PFEvaluator(this.store).evaluate(user);
    }
}
