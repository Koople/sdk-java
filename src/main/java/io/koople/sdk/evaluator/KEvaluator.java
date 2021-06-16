package io.koople.sdk.evaluator;

import io.koople.sdk.evaluator.stores.KInMemoryStore;

import java.util.HashMap;
import java.util.List;

public class KEvaluator {
    private final KStore store;

    public KEvaluator(KStore store) {
        this.store = store;
    }

    public KEvaluator(List<KFeatureFlag> featureFlags, List<KRemoteConfig> remoteConfigs, List<KSegment> segments) {
        this(new KInMemoryStore(featureFlags, remoteConfigs, segments));
    }

    public KEvaluation evaluate(KUser user) {
        HashMap<String, Boolean> featuresFlags = new HashMap<>();
        HashMap<String, String> remoteConfigs = new HashMap<>();

        for (KFeatureFlag featureFlag : this.store.getAllFeaturesFlags())
            featuresFlags.put(featureFlag.key, featureFlag.evaluate(store, user));

        for (KRemoteConfig remoteConfig : this.store.getAllRemoteConfigs())
            remoteConfigs.put(remoteConfig.key, remoteConfig.evaluate(store, user));

        return new KEvaluation(featuresFlags, remoteConfigs);
    }

    public String valueOf(String remoteConfig, KUser user, String defaultValue)
    {
        KRemoteConfig rc = store.getRemoteConfig(remoteConfig);

        if(rc != null)
            return rc.evaluate(store, user);

        return defaultValue;
    }
}
