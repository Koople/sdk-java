package io.koople.sdk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.sdk.evaluator.KFeatureFlag;
import io.koople.sdk.evaluator.KRemoteConfig;
import io.koople.sdk.evaluator.KSegment;

import java.util.List;

public class ServerInitializeResponseDTO {
    final List<KFeatureFlag> features;
    final List<KRemoteConfig> remoteConfigs;
    final List<KSegment> segments;

    @JsonCreator
    public ServerInitializeResponseDTO(@JsonProperty("features") List<KFeatureFlag> features, @JsonProperty("remoteConfigs") List<KRemoteConfig> remoteConfigs, @JsonProperty("segments") List<KSegment> segments) {
        this.features = features;
        this.remoteConfigs = remoteConfigs;
        this.segments = segments;
    }
}
