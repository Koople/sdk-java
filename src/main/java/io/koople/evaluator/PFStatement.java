package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFUser;
import io.koople.evaluator.PFValue;
import io.koople.evaluator.statements.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, include = PROPERTY, property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = PFEqualsStatement.class, name = "equals"),
    @JsonSubTypes.Type(value = PFNotEqualsStatement.class, name = "notEquals"),
    @JsonSubTypes.Type(value = PFExistsStatement.class, name = "exists"),
    @JsonSubTypes.Type(value = PFNotExistsStatement.class, name = "notExists"),
    @JsonSubTypes.Type(value = PFContainsStatement.class, name = "contains"),
    @JsonSubTypes.Type(value = PFNotContainsStatement.class, name = "notContains"),
    @JsonSubTypes.Type(value = PFGreaterThanStatement.class, name = "greaterThan"),
    @JsonSubTypes.Type(value = PFGreaterThanOrEqualsStatement.class, name = "greaterThanOrEquals"),
    @JsonSubTypes.Type(value = PFLessThanStatement.class, name = "lessThan"),
    @JsonSubTypes.Type(value = PFLessThanOrEqualsStatement.class, name = "lessThanOrEquals"),
    @JsonSubTypes.Type(value = PFIsTruthyStatement.class, name = "isTruthy"),
    @JsonSubTypes.Type(value = PFIsFalsyStatement.class, name = "isFalsy"),
    @JsonSubTypes.Type(value = PFSegmentMatchStatement.class, name = "segmentMatch"),
    @JsonSubTypes.Type(value = PFSegmentNotMatchStatement.class, name = "segmentNotMatch")
})
public abstract class PFStatement<T extends PFValue> implements Serializable {
    public final String attribute;
    public final List<T> values;

    @JsonCreator
    public PFStatement(@JsonProperty("attribute") String attribute, @JsonProperty("values") @NotNull List<T> values) {
        this.attribute = attribute;
        this.values = values;
    }

    public abstract boolean evaluate(PFStore store, PFUser user);
}

