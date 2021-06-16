package io.koople.sdk.evaluator.values;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.sdk.evaluator.KValue;

public class KSegmentValue extends KValue<String> {

    @JsonCreator
    public KSegmentValue(@JsonProperty("value") String value) {
        super(value);
    }

    public String key() {
        return value;
    }
}
