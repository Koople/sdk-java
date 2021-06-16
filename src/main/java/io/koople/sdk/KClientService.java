package io.koople.sdk;

import io.koople.sdk.evaluator.*;
import io.koople.sdk.evaluator.stores.KInMemoryStore;

import java.io.IOException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class KClientService {

    private KStore store;
    private final String apiKey;
    private final int pollingInterval;
    private final KHttpClient httpClient;
    private final KConfig config;


    public KClientService(String apiKey, int pollingInterval) throws IOException {
        this.apiKey = apiKey;
        this.pollingInterval = pollingInterval * 1000;
        this.config = new KConfig(apiKey);
        this.httpClient = new KHttpClient();

        FetchStore();
        setIntervalTask();

    }

    private void setIntervalTask() {
        TimerTask task = new TimerTask() {
            public void run() {
                try {
                    FetchStore();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer("FetchStoreTimer");
        timer.schedule(task, this.pollingInterval, this.pollingInterval);
    }

    private void FetchStore() throws IOException {
        ServerInitializeResponseDTO dto = httpClient.get(config.init(), apiKey);
        this.store = new KInMemoryStore(dto.features, dto.remoteConfigs, dto.segments);
    }

    public KEvaluation evaluate(KUser user) {
        return new KEvaluator(this.store).evaluate(user);
    }

    public Boolean IsEnabled(String feature, KUser user) {
        Optional<KFeatureFlag> featureFlag = this.store.getAllFeaturesFlags().stream().filter(x -> x.key.equals(feature)).findFirst();
        //KFeatureFlag featureFlag = this.store.findFeatureFlagByKey(feature);

        return featureFlag
                .map(kFeatureFlag -> kFeatureFlag.evaluate(this.store, user))
                .orElse(false);

    }

    public String valueOf(String remoteConfig, KUser user, String defaultValue) {
        return new KEvaluator(this.store).valueOf(remoteConfig, user, defaultValue);
    }
}
