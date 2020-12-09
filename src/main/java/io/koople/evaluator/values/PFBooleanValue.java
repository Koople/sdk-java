package io.koople.evaluator.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFValue;

public class PFBooleanValue extends PFValue<Boolean> {

    @JsonCreator
    public PFBooleanValue(@JsonProperty("value") Boolean value) {
        super(value);
    }

    public static io.koople.evaluator.values.PFBooleanValue True() {
        return new io.koople.evaluator.values.PFBooleanValue(true);
    }

    public static io.koople.evaluator.values.PFBooleanValue False() {
        return new io.koople.evaluator.values.PFBooleanValue(false);
    }

    public boolean isTruthy() {
        return value;
    }

    public boolean isFalsy() {
        return !value;
    }
}
