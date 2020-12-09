package io.koople.evaluator.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFValue;

public class PFNumberValue extends PFValue<Integer> {

    @JsonCreator
    public PFNumberValue(@JsonProperty("value") Integer value) {
        super(value);
    }

    public boolean greaterThan(io.koople.evaluator.values.PFNumberValue other) {
        return value > other.value;
    }

    public boolean greaterThanOrEquals(io.koople.evaluator.values.PFNumberValue other) {
        return value >= other.value;
    }

    public boolean lessThan(io.koople.evaluator.values.PFNumberValue other) {
        return value < other.value;
    }

    public boolean lessThanOrEquals(io.koople.evaluator.values.PFNumberValue other) {
        return value <= other.value;
    }
}
