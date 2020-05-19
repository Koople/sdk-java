package com.pataflags.evaluator;

import java.util.ArrayList;
import java.util.List;

public class Feature {
    public final String key;
    private final FeatureTargeting targeting;
    private final List<String> identities;
    private final List<Segment> segments;

    public Feature(String key, FeatureTargeting targeting) {
        this(key, targeting, new ArrayList<String>(), new ArrayList<Segment>());
    }

    public Feature(String key, FeatureTargeting targeting, List<String> identities, List<Segment> segments) {
        this.key = key;
        this.targeting = targeting;
        this.identities = identities;
        this.segments = segments;
    }

    public boolean evaluate(User user) {
        if (targeting.equals(FeatureTargeting.all))
            return true;

        if (targeting.equals(FeatureTargeting.segment)) {
            if (identities.contains(user.getIdentity()))
                return true;

            for (Segment segment : segments)
                if (segment.evaluate(user))
                    return true;
        }

        return false;
    }
}
