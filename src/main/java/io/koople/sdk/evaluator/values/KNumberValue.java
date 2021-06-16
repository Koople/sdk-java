package io.koople.sdk.evaluator.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.sdk.evaluator.KValue;

public class KNumberValue extends KValue<Integer> {

    @JsonCreator
    public KNumberValue(@JsonProperty("value") Integer value) {
        super(value);
    }

    public boolean greaterThan(KNumberValue other) {
        return value > other.value;
    }

    public boolean greaterThanOrEquals(KNumberValue other) {
        return value >= other.value;
    }

    public boolean lessThan(KNumberValue other) {
        return value < other.value;
    }

    public boolean lessThanOrEquals(KNumberValue other) {
        return value <= other.value;
    }
}
