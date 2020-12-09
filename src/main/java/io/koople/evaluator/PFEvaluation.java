package io.koople.evaluator;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

public class PFEvaluation implements Serializable {
    public final Map<String, Boolean> features;
    public final Map<String, String> remoteConfigs;

    PFEvaluation(Map<String, Boolean> features, Map<String, String> remoteConfigs) {
        this.features = features;
        this.remoteConfigs = remoteConfigs;
    }

    public Map<String, Boolean> onlyEnabled() {
        return this.features
                .entrySet()
                .stream()
                .filter(Map.Entry::getValue)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
