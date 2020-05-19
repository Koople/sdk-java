package com.pataflags.evaluator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Evaluator {
    private final List<Feature> features;

    public Evaluator(List<Feature> features) {
        this.features = features;
    }

    public Map<String, Boolean> evaluate(User user) {
        HashMap<String, Boolean> evaluation = new HashMap<>();

        for (Feature feature: features)
            evaluation.put(feature.key, feature.evaluate(user));

        return evaluation;
    }
}
