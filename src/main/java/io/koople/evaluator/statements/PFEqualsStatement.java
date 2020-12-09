package io.koople.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFStatement;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFUser;
import io.koople.evaluator.PFValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PFEqualsStatement extends PFStatement<PFValue> {

    @JsonCreator
    public PFEqualsStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<PFValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(PFStore store, PFUser user) {
        PFValue userValue = user.getValue(attribute);

        if (userValue != null)
            for (PFValue value : values)
                if (userValue.equals(value))
                    return true;

        return false;
    }

    public static io.koople.evaluator.statements.PFEqualsStatement of(String attribute, Object... values) {
        return new io.koople.evaluator.statements.PFEqualsStatement(attribute, new ArrayList<PFValue>() {{
            Arrays.stream(values).filter(Objects::nonNull).forEach(it -> add(PFValue.create(it)));
        }});
    }
}

