package io.koople.sdk.evaluator;

import java.util.List;

public interface KStore {
    List<KFeatureFlag> getAllFeaturesFlags();
    List<KRemoteConfig> getAllRemoteConfigs();
    KSegment findSegmentByKey(String key);
    KFeatureFlag findFeatureFlagByKey(String key);
    KRemoteConfig getRemoteConfig(String remoteConfig);
}
