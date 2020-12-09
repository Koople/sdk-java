package io.koople.evaluator.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFValue;

public class PFStringValue extends PFValue<String> {

    @JsonCreator
    public PFStringValue(@JsonProperty("value") String value) {
        super(value);
    }

    public boolean isEmpty() { return value == ""; }
    public boolean isNonEmpty() { return !this.isEmpty(); }
    public boolean contains(io.koople.evaluator.values.PFStringValue other) {
        return value.contains(other.value);
    }
}
