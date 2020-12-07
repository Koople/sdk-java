package io.koople.sdk;

import io.koople.evaluator.*;
import io.koople.evaluator.stores.PFInMemoryStore;

import java.io.IOException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class KClientService {

    private PFStore store;
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
        this.store = new PFInMemoryStore(dto.features, dto.remoteConfigs, dto.segments);
    }

    public PFEvaluation evaluate(PFUser user) {
        return new PFEvaluator(this.store).evaluate(user);
    }

    public Boolean IsEnabled(String feature, PFUser user) {
        Optional<PFFeatureFlag> featureFlag = this.store.getAllFeaturesFlags().stream().filter(x -> x.key.equals(feature)).findFirst();
        //PFFeatureFlag featureFlag = this.store.findFeatureFlagByKey(feature);

        return featureFlag
                .map(pfFeatureFlag -> pfFeatureFlag.evaluate(this.store, user))
                .orElse(false);

    }

    public String valueOf(String remoteConfig, PFUser user, String defaultValue) {
        return new PFEvaluator(this.store).valueOf(remoteConfig, user, defaultValue);
    }
}
