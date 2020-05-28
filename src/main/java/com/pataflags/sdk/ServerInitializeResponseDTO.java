package com.pataflags.sdk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pataflags.evaluator.PFFeatureFlag;
import com.pataflags.evaluator.PFSegment;

import java.util.List;

public class ServerInitializeResponseDTO {
    final List<PFFeatureFlag> features;
    final List<PFSegment> segments;

    @JsonCreator
    public ServerInitializeResponseDTO(@JsonProperty("features") List<PFFeatureFlag> features, @JsonProperty("segments") List<PFSegment> segments) {
        this.features = features;
        this.segments = segments;
    }
}
