package io.koople.evaluator;

import io.koople.evaluator.PFEvaluation;
import io.koople.evaluator.PFFeatureFlag;
import io.koople.evaluator.PFRemoteConfig;
import io.koople.evaluator.PFSegment;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFUser;
import io.koople.evaluator.stores.PFInMemoryStore;

import java.util.HashMap;
import java.util.List;

public class PFEvaluator {
    private final PFStore store;

    public PFEvaluator(PFStore store) {
        this.store = store;
    }

    public PFEvaluator(List<PFFeatureFlag> featureFlags, List<PFRemoteConfig> remoteConfigs, List<PFSegment> segments) {
        this(new PFInMemoryStore(featureFlags, remoteConfigs, segments));
    }

    public PFEvaluation evaluate(PFUser user) {
        HashMap<String, Boolean> featuresFlags = new HashMap<>();
        HashMap<String, String> remoteConfigs = new HashMap<>();

        for (PFFeatureFlag featureFlag : this.store.getAllFeaturesFlags())
            featuresFlags.put(featureFlag.key, featureFlag.evaluate(store, user));

        for (PFRemoteConfig remoteConfig : this.store.getAllRemoteConfigs())
            remoteConfigs.put(remoteConfig.key, remoteConfig.evaluate(store, user));

        return new PFEvaluation(featuresFlags, remoteConfigs);
    }

    public String valueOf(String remoteConfig, PFUser user, String defaultValue)
    {
        PFRemoteConfig rc = store.getRemoteConfig(remoteConfig);

        if(rc != null)
            return rc.evaluate(store, user);

        return defaultValue;
    }
}
