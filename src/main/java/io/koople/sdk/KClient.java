package io.koople.sdk;

import io.koople.sdk.evaluator.KEvaluation;
import io.koople.sdk.evaluator.KUser;

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

    public KEvaluation evaluate(KUser user) {
        return clientService.evaluate(user);
    }

    public boolean isEnabled(String feature, KUser user) {
        return clientService.IsEnabled(feature, user);
    }

    public boolean isEnabled(String feature) {
        return clientService.IsEnabled(feature, KUser.anonymous());
    }

    public String valueOf(String remoteConfig) {
        return clientService.valueOf(remoteConfig, KUser.anonymous(), "");
    }

    public String valueOf(String remoteConfig, KUser user) {
        return valueOf(remoteConfig, user, "");
    }

    public String valueOf(String remoteConfig, String defaultValue) {
        return clientService.valueOf(remoteConfig, KUser.anonymous(), defaultValue);
    }

    public String valueOf(String remoteConfig, KUser user, String defaultValue) {
        return clientService.valueOf(remoteConfig, user, defaultValue);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Closing!");
    }
}
