package io.koople.evaluator.statements;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.KSegment;
import io.koople.evaluator.KStatement;
import io.koople.evaluator.KStore;
import io.koople.evaluator.KUser;
import io.koople.evaluator.values.KSegmentValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KSegmentMatchStatement extends KStatement<KSegmentValue> {

    @JsonCreator
    public KSegmentMatchStatement(@JsonProperty("values") @NotNull List<KSegmentValue> values) {
        super(null, values);
    }

    @Override
    public boolean evaluate(KStore store, KUser user) {
        for (KSegmentValue value : values) {
            KSegment segment = store.findSegmentByKey(value.key());
            if (segment != null)
                if (segment.evaluate(store, user))
                    return true;
        }

        return false;
    }

    public static KSegmentMatchStatement of(String... values) {
        return new KSegmentMatchStatement(new ArrayList<KSegmentValue>() {{
            Arrays.stream(values).forEach(it -> {
                if (it != null)
                    add(new KSegmentValue(it));
            });
        }});
    }
}

