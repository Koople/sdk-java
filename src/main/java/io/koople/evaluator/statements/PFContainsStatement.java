package io.koople.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFStatement;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFUser;
import io.koople.evaluator.values.PFStringValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PFContainsStatement extends PFStatement<PFStringValue> {

    @JsonCreator
    public PFContainsStatement(@JsonProperty("attribute") @NotNull String attribute, @JsonProperty("values") @NotNull List<PFStringValue> values) {
        super(attribute, values);
    }

    @Override
    public boolean evaluate(PFStore store, PFUser user) {
        PFStringValue userValue = user.getStringValue(attribute);

        if (userValue != null)
            for (PFStringValue value : values)
                if (userValue.contains(value))
                    return true;

        return false;
    }

    public static io.koople.evaluator.statements.PFContainsStatement of(String attribute, Object... values) {
        return new io.koople.evaluator.statements.PFContainsStatement(attribute, new ArrayList<PFStringValue>() {{
            Arrays.stream(values).forEach(it -> {
                if (it instanceof String)
                    add(new PFStringValue(it.toString()));
            });
        }});
    }
}

