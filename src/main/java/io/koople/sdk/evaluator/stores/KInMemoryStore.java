package io.koople.sdk.evaluator.stores;

import io.koople.sdk.evaluator.KFeatureFlag;
import io.koople.sdk.evaluator.KRemoteConfig;
import io.koople.sdk.evaluator.KSegment;
import io.koople.sdk.evaluator.KStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KInMemoryStore implements KStore {
    private Map<String, KFeatureFlag> featureFlags;
    private Map<String, KRemoteConfig> remoteConfigs;
    private Map<String, KSegment> segments;

    public KInMemoryStore(List<KFeatureFlag> featureFlags, List<KRemoteConfig> remoteConfigs, List<KSegment> segments) {
        this.featureFlags = new HashMap<String, KFeatureFlag>() {{
            featureFlags.forEach((f) -> put(f.key, f));
        }};
        this.remoteConfigs = new HashMap<String, KRemoteConfig>() {{
            remoteConfigs.forEach((r) -> put(r.key, r));
        }};
        this.segments = new HashMap<String, KSegment>() {{
            segments.forEach((s) -> put(s.key, s));
        }};
    }

    @Override
    public List<KFeatureFlag> getAllFeaturesFlags() {
        return new ArrayList<>(featureFlags.values());
    }

    @Override
    public List<KRemoteConfig> getAllRemoteConfigs() {
        return new ArrayList<>(remoteConfigs.values());
    }

    @Override
    public KSegment findSegmentByKey(String key) {
        return this.segments.get(key);
    }

    @Override
    public KFeatureFlag findFeatureFlagByKey(String key) { return this.featureFlags.get(key); }

    @Override
    public KRemoteConfig getRemoteConfig(String remoteConfig) { return this.remoteConfigs.get(remoteConfig); }


}
