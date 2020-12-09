package io.koople.evaluator.stores;

import io.koople.evaluator.PFFeatureFlag;
import io.koople.evaluator.PFRemoteConfig;
import io.koople.evaluator.PFSegment;
import io.koople.evaluator.PFStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PFInMemoryStore implements PFStore {
    private Map<String, PFFeatureFlag> featureFlags;
    private Map<String, PFRemoteConfig> remoteConfigs;
    private Map<String, PFSegment> segments;

    public PFInMemoryStore(List<PFFeatureFlag> featureFlags, List<PFRemoteConfig> remoteConfigs, List<PFSegment> segments) {
        this.featureFlags = new HashMap<String, PFFeatureFlag>() {{
            featureFlags.forEach((f) -> put(f.key, f));
        }};
        this.remoteConfigs = new HashMap<String, PFRemoteConfig>() {{
            remoteConfigs.forEach((r) -> put(r.key, r));
        }};
        this.segments = new HashMap<String, PFSegment>() {{
            segments.forEach((s) -> put(s.key, s));
        }};
    }

    @Override
    public List<PFFeatureFlag> getAllFeaturesFlags() {
        return new ArrayList<>(featureFlags.values());
    }

    @Override
    public List<PFRemoteConfig> getAllRemoteConfigs() {
        return new ArrayList<>(remoteConfigs.values());
    }

    @Override
    public PFSegment findSegmentByKey(String key) {
        return this.segments.get(key);
    }

    @Override
    public PFFeatureFlag findFeatureFlagByKey(String key) { return this.featureFlags.get(key); }

    @Override
    public PFRemoteConfig getRemoteConfig(String remoteConfig) { return this.remoteConfigs.get(remoteConfig); }


}
