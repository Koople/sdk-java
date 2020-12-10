package io.koople.evaluator.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.KValue;

public class KBooleanValue extends KValue<Boolean> {

    @JsonCreator
    public KBooleanValue(@JsonProperty("value") Boolean value) {
        super(value);
    }

    public static KBooleanValue True() {
        return new KBooleanValue(true);
    }

    public static KBooleanValue False() {
        return new KBooleanValue(false);
    }

    public boolean isTruthy() {
        return value;
    }

    public boolean isFalsy() {
        return !value;
    }
}
