package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KFeatureFlag implements Serializable {
    public final String key;
    public final KTargeting targeting;
    public final List<String> identities;
    public final List<KFInlineRule> rules;
    public final Boolean enableRollout;
    public final KRollout rollout;


    public KFeatureFlag(String key, KTargeting targeting) {
        this(key, targeting, new ArrayList<>(), new ArrayList<>(), false, null);
    }

    @JsonCreator
    public KFeatureFlag(@JsonProperty("key") String key, @JsonProperty("targeting") KTargeting targeting, @JsonProperty("identities") List<String> identities, @JsonProperty("rules") List<KFInlineRule> rules, @JsonProperty("enableRollout") Boolean enableRollout, @JsonProperty("rollout") KRollout rollout) {
        this.key = key;
        this.targeting = targeting;
        this.identities = identities;
        this.rules = rules;
        this.enableRollout = enableRollout;
        this.rollout = rollout;
    }

    public boolean evaluate(KStore store, KUser user) {
        if (targeting.equals(KTargeting.ENABLED_FOR_ALL))
            return true;

        if (targeting.equals(KTargeting.ENABLED_FOR_SOME_USERS)) {
            if (identities.contains(user.getIdentity())) return true;
            if (enableRollout && !rollout.evaluate(key + user.identity)) return false;
            if (enableRollout && rules.size() == 0) return true;

            for (KFInlineRule rule : rules)
                if (rule.evaluate(store, user))
                    return true;
        }

        return false;
    }
}
