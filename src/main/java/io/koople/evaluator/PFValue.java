package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.koople.evaluator.exceptions.UserAttributeTypeNotSupported;
import io.koople.evaluator.values.PFBooleanValue;
import io.koople.evaluator.values.PFNumberValue;
import io.koople.evaluator.values.PFSegmentValue;
import io.koople.evaluator.values.PFStringValue;

import java.io.Serializable;
import java.util.LinkedHashMap;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, include = PROPERTY, property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PFStringValue.class, name = "string"),
        @JsonSubTypes.Type(value = PFNumberValue.class, name = "number"),
        @JsonSubTypes.Type(value = PFBooleanValue.class, name = "boolean"),
        @JsonSubTypes.Type(value = PFSegmentValue.class, name = "segment"),
})
public abstract class PFValue<T> implements Serializable {
    public final T value;

    @JsonCreator
    public PFValue(@JsonProperty("value") T value) {
        this.value = value;
    }

    public boolean equals(io.koople.evaluator.PFValue other) {
        if (getClass().equals(other.getClass()))
            return value.equals(other.value);

        return false;
    }

    public boolean notEquals(io.koople.evaluator.PFValue other) {
        return !equals(other);
    }

    public static io.koople.evaluator.PFValue create(Object value) {
        if (value instanceof String)
            return new PFStringValue(String.valueOf(value));
        else if (value instanceof Integer)
            return new PFNumberValue((Integer) value);
        else if (value instanceof Boolean)
            return new PFBooleanValue((Boolean) value);
        else if (value instanceof LinkedHashMap)
            throw new UserAttributeTypeNotSupported("not supported nested objects");
        else if (value == null) // TODO we should forbid this values, not throw an exception.
            throw new UserAttributeTypeNotSupported("not supported null values");
        else
            throw new UserAttributeTypeNotSupported("not supported value type " + value.getClass().getName());

    }

    public PFStringValue asString() {
        if (this instanceof PFStringValue)
            return (PFStringValue) this;

        return null;
    }

    public PFNumberValue asNumber() {
        if (this instanceof PFNumberValue)
            return (PFNumberValue) this;

        return null;
    }

    public PFBooleanValue asBoolean() {
        if (this instanceof PFBooleanValue)
            return (PFBooleanValue) this;

        return null;
    }
}
