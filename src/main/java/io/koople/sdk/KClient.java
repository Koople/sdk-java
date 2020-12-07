package io.koople.sdk;

import io.koople.evaluator.PFEvaluation;
import io.koople.evaluator.PFUser;

import java.io.IOException;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;


public class KClient implements AutoCloseable {

    private KClientService clientService;
    private static final int DEFAULT_POLLING_INTERVAL = 60;

    private KClient(KClientService clientService) {
        this.clientService = clientService;
    }

    public static KClient initialize(String apiKey) throws IOException {
        checkNotNull(apiKey, "Sdk key must not be null");
        return initialize(apiKey, DEFAULT_POLLING_INTERVAL);
    }

    public static KClient initialize(String apiKey, int pollingInterval) throws IOException {
        checkNotNull(apiKey, "Sdk key must not be null");
        if(pollingInterval < 10) pollingInterval = 10;

        KClientService clientService = new KClientService(apiKey, pollingInterval);
        return new KClient(clientService);
    }

    public PFEvaluation evaluate(PFUser user) {
        return clientService.evaluate(user);
    }

    public Boolean isEnabled(String feature, PFUser user) {
        return clientService.IsEnabled(feature, user);
    }

    public Boolean isEnabled(String feature) {
        return clientService.IsEnabled(feature, PFUser.anonymous());
    }

    public String valueOf(String remoteConfig) {
        return clientService.valueOf(remoteConfig, PFUser.anonymous(), "");
    }

    public String valueOf(String remoteConfig, PFUser user) {
        return valueOf(remoteConfig, user, "");
    }

    public String valueOf(String remoteConfig, String defaultValue) {
        return clientService.valueOf(remoteConfig, PFUser.anonymous(), defaultValue);
    }

    public String valueOf(String remoteConfig, PFUser user, String defaultValue) {
        return clientService.valueOf(remoteConfig, user, defaultValue);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Closing!");
    }
}
