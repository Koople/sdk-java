package io.koople.sdk.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.koople.sdk.evaluator.statements.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, include = PROPERTY, property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = KEqualsStatement.class, name = "equals"),
    @JsonSubTypes.Type(value = KNotEqualsStatement.class, name = "notEquals"),
    @JsonSubTypes.Type(value = KExistsStatement.class, name = "exists"),
    @JsonSubTypes.Type(value = KNotExistsStatement.class, name = "notExists"),
    @JsonSubTypes.Type(value = KContainsStatement.class, name = "contains"),
    @JsonSubTypes.Type(value = KNotContainsStatement.class, name = "notContains"),
    @JsonSubTypes.Type(value = KGreaterThanStatement.class, name = "greaterThan"),
    @JsonSubTypes.Type(value = KGreaterThanOrEqualsStatement.class, name = "greaterThanOrEquals"),
    @JsonSubTypes.Type(value = KLessThanStatement.class, name = "lessThan"),
    @JsonSubTypes.Type(value = KLessThanOrEqualsStatement.class, name = "lessThanOrEquals"),
    @JsonSubTypes.Type(value = KIsTruthyStatement.class, name = "isTruthy"),
    @JsonSubTypes.Type(value = KIsFalsyStatement.class, name = "isFalsy"),
    @JsonSubTypes.Type(value = KSegmentMatchStatement.class, name = "segmentMatch"),
    @JsonSubTypes.Type(value = KSegmentNotMatchStatement.class, name = "segmentNotMatch")
})
public abstract class KStatement<T extends KValue> implements Serializable {
    public final String attribute;
    public final List<T> values;

    @JsonCreator
    public KStatement(@JsonProperty("attribute") String attribute, @JsonProperty("values") @NotNull List<T> values) {
        this.attribute = attribute;
        this.values = values;
    }

    public abstract boolean evaluate(KStore store, KUser user);
}

