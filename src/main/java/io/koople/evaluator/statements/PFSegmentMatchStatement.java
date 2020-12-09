package io.koople.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFSegment;
import io.koople.evaluator.PFStatement;
import io.koople.evaluator.PFStore;
import io.koople.evaluator.PFUser;
import io.koople.evaluator.values.PFSegmentValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PFSegmentMatchStatement extends PFStatement<PFSegmentValue> {

    @JsonCreator
    public PFSegmentMatchStatement(@JsonProperty("values") @NotNull List<PFSegmentValue> values) {
        super(null, values);
    }

    @Override
    public boolean evaluate(PFStore store, PFUser user) {
        for (PFSegmentValue value : values) {
            PFSegment segment = store.findSegmentByKey(value.key());
            if (segment != null)
                if (segment.evaluate(store, user))
                    return true;
        }

        return false;
    }

    public static io.koople.evaluator.statements.PFSegmentMatchStatement of(String... values) {
        return new io.koople.evaluator.statements.PFSegmentMatchStatement(new ArrayList<PFSegmentValue>() {{
            Arrays.stream(values).forEach(it -> {
                if (it != null)
                    add(new PFSegmentValue(it));
            });
        }});
    }
}

