package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.koople.evaluator.rollouts.PFPercentageRollout;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, include = PROPERTY, property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = PFPercentageRollout.class, name = "percentage")
})
public abstract class PFRollout implements Serializable {
    public abstract Boolean evaluate(String identifier);

    public static io.koople.evaluator.PFRollout percentage(int percentage) {
        return new PFPercentageRollout(percentage);
    }
}
