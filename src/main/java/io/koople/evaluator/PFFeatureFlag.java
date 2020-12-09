package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFInlineRule;
import io.koople.evaluator.PFRollout;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFTargeting;
import io.koople.evaluator.PFUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PFFeatureFlag implements Serializable {
    public final String key;
    public final PFTargeting targeting;
    public final List<String> identities;
    public final List<PFInlineRule> rules;
    public final Boolean enableRollout;
    public final PFRollout rollout;


    public PFFeatureFlag(String key, PFTargeting targeting) {
        this(key, targeting, new ArrayList<>(), new ArrayList<>(), false, null);
    }

    @JsonCreator
    public PFFeatureFlag(@JsonProperty("key") String key, @JsonProperty("targeting") PFTargeting targeting, @JsonProperty("identities") List<String> identities, @JsonProperty("rules") List<PFInlineRule> rules, @JsonProperty("enableRollout") Boolean enableRollout, @JsonProperty("rollout") PFRollout rollout) {
        this.key = key;
        this.targeting = targeting;
        this.identities = identities;
        this.rules = rules;
        this.enableRollout = enableRollout;
        this.rollout = rollout;
    }

    public boolean evaluate(PFStore store, PFUser user) {
        if (targeting.equals(PFTargeting.ENABLED_FOR_ALL))
            return true;

        if (targeting.equals(PFTargeting.ENABLED_FOR_SOME_USERS)) {
            if (identities.contains(user.getIdentity())) return true;
            if (enableRollout && !rollout.evaluate(key + user.identity)) return false;
            if (enableRollout && rules.size() == 0) return true;

            for (PFInlineRule rule : rules)
                if (rule.evaluate(store, user))
                    return true;
        }

        return false;
    }
}
