package io.koople.sdk.evaluator;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.koople.sdk.evaluator.rollouts.KPercentageRollout;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, include = PROPERTY, property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = KPercentageRollout.class, name = "percentage")
})
public abstract class KRollout implements Serializable {
    public abstract Boolean evaluate(String identifier);

    public static KRollout percentage(int percentage) {
        return new KPercentageRollout(percentage);
    }
}
