package io.koople.evaluator.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFValue;

public class PFSegmentValue extends PFValue<String> {

    @JsonCreator
    public PFSegmentValue(@JsonProperty("value") String value) {
        super(value);
    }

    public String key() {
        return value;
    }
}
