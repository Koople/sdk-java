package io.koople.sdk.evaluator.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.sdk.evaluator.KValue;

public class KStringValue extends KValue<String> {

    @JsonCreator
    public KStringValue(@JsonProperty("value") String value) {
        super(value);
    }

    public boolean isEmpty() { return value == ""; }
    public boolean isNonEmpty() { return !this.isEmpty(); }
    public boolean contains(KStringValue other) {
        return value.contains(other.value);
    }
}
