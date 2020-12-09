package io.koople.evaluator;

import io.koople.evaluator.PFFeatureFlag;
import io.koople.evaluator.PFRemoteConfig;
import io.koople.evaluator.PFSegment;

import java.util.List;

public interface PFStore {
    List<PFFeatureFlag> getAllFeaturesFlags();
    List<PFRemoteConfig> getAllRemoteConfigs();
    PFSegment findSegmentByKey(String key);
    PFFeatureFlag findFeatureFlagByKey(String key);
    PFRemoteConfig getRemoteConfig(String remoteConfig);
}
