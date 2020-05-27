package com.pataflags.sdk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pataflags.evaluator.Feature;
import com.pataflags.evaluator.Segment;

import java.util.List;

public class ServerInitializeResponseDTO {
    final List<Feature> features;
    final List<Segment> segments;

    @JsonCreator
    public ServerInitializeResponseDTO(@JsonProperty("features") List<Feature> features, @JsonProperty("segments") List<Segment> segments) {
        this.features = features;
        this.segments = segments;
    }
}
