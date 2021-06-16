package io.koople.sdk.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.koople.sdk.evaluator.exceptions.UserAttributeTypeNotSupported;
import io.koople.sdk.evaluator.values.KBooleanValue;
import io.koople.sdk.evaluator.values.KNumberValue;
import io.koople.sdk.evaluator.values.KSegmentValue;
import io.koople.sdk.evaluator.values.KStringValue;

import java.io.Serializable;
import java.util.LinkedHashMap;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, include = PROPERTY, property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = KStringValue.class, name = "string"),
        @JsonSubTypes.Type(value = KNumberValue.class, name = "number"),
        @JsonSubTypes.Type(value = KBooleanValue.class, name = "boolean"),
        @JsonSubTypes.Type(value = KSegmentValue.class, name = "segment"),
})
public abstract class KValue<T> implements Serializable {
    public final T value;

    @JsonCreator
    public KValue(@JsonProperty("value") T value) {
        this.value = value;
    }

    public boolean equals(KValue other) {
        if (getClass().equals(other.getClass()))
            return value.equals(other.value);

        return false;
    }

    public boolean notEquals(KValue other) {
        return !equals(other);
    }

    public static KValue create(Object value) {
        if (value instanceof String)
            return new KStringValue(String.valueOf(value));
        else if (value instanceof Integer)
            return new KNumberValue((Integer) value);
        else if (value instanceof Boolean)
            return new KBooleanValue((Boolean) value);
        else if (value instanceof LinkedHashMap)
            throw new UserAttributeTypeNotSupported("not supported nested objects");
        else if (value == null) // TODO we should forbid this values, not throw an exception.
            throw new UserAttributeTypeNotSupported("not supported null values");
        else
            throw new UserAttributeTypeNotSupported("not supported value type " + value.getClass().getName());

    }

    public KStringValue asString() {
        if (this instanceof KStringValue)
            return (KStringValue) this;

        return null;
    }

    public KNumberValue asNumber() {
        if (this instanceof KNumberValue)
            return (KNumberValue) this;

        return null;
    }

    public KBooleanValue asBoolean() {
        if (this instanceof KBooleanValue)
            return (KBooleanValue) this;

        return null;
    }
}
