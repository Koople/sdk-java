package io.koople.evaluator.rollouts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFRollout;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;

public class PFPercentageRollout extends PFRollout {
    public final int percentage;

    @JsonCreator
    public PFPercentageRollout (@JsonProperty("percentage") @NotNull int percentage) {
        this.percentage = percentage;
    }

    public Boolean evaluate(String identifier) {
        String hash = DigestUtils.shaHex(identifier).substring(0, 7);
        int value = Integer.parseInt(hash, 16) % 100;
        return percentage >= value;
    }
}
