package io.koople.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.evaluator.PFUserAttribute;
import io.koople.evaluator.PFValue;
import io.koople.evaluator.values.PFBooleanValue;
import io.koople.evaluator.values.PFNumberValue;
import io.koople.evaluator.values.PFStringValue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PFUser implements Serializable {

    public final String identity;
    public final Map<String, PFValue> attributes;

    @JsonCreator
    public PFUser(@JsonProperty("identity") String identity, @JsonProperty("attributes") Map<String, PFValue> attributes) {
        this.identity = identity;
        this.attributes = attributes;
    }

    String getIdentity() {
        return identity;
    }

    public PFValue getValue(String attribute) {
        return attributes.get(attribute);
    }

    public PFStringValue getStringValue(String attribute) {
        PFValue value = attributes.get(attribute);

        if (value != null)
            return value.asString();

        return null;
    }

    public PFBooleanValue getBooleanValue(String attribute) {
        PFValue value = attributes.get(attribute);

        if (value != null)
            return value.asBoolean();

        return null;
    }

    public PFNumberValue getNumberValue(String attribute) {
        PFValue value = attributes.get(attribute);

        if (value != null)
            return value.asNumber();

        return null;
    }

    public static io.koople.evaluator.PFUser create(String identity) {
        return new io.koople.evaluator.PFUser(identity, new HashMap<String, PFValue>() {{ }});
    }

    public static io.koople.evaluator.PFUser create(String identity, Map<String, Object> attributes) {
        return new io.koople.evaluator.PFUser(identity, new HashMap<String, PFValue>() {{
            attributes.forEach((key, value) -> {
                if (value != null) put(key, PFValue.create(value));
            });
        }});
    }

    public static io.koople.evaluator.PFUser create(String identity, List<PFUserAttribute> attributes) {
        return new io.koople.evaluator.PFUser(identity, new HashMap<String, PFValue>() {{
            attributes.forEach((it) -> {
                if (it.value != null) put(it.name, PFValue.create(it.value));
            });
        }});
    }

    public static io.koople.evaluator.PFUser anonymous() {
        return create("anonymous-${UUID.randomUUID().toString()}");
    }

//    // TODO remove only for test
//    public static PFUser create(String identity, Attribute... attributes) {
//        return new PFUser(identity, new HashMap<String, PFValue>() {{
//            Arrays.stream(attributes).filter(Objects::nonNull).forEach(it -> put(it.name, PFValue.create(it.value)));
//        }});
//    }

    public io.koople.evaluator.PFUser with(PFUserAttribute attribute) {
        if(attribute.value != null) this.attributes.put(attribute.name, PFValue.create(attribute.value));
        return this;
    }

    public io.koople.evaluator.PFUser with(String name, Object value) {
        if(value != null) this.attributes.put(name, PFValue.create(value));
        return this;
    }
}
