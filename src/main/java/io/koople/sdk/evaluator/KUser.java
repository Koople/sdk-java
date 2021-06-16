package io.koople.sdk.evaluator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.koople.sdk.evaluator.values.KBooleanValue;
import io.koople.sdk.evaluator.values.KNumberValue;
import io.koople.sdk.evaluator.values.KStringValue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KUser implements Serializable {

    public final String identity;
    public final Map<String, KValue> attributes;

    @JsonCreator
    public KUser(@JsonProperty("identity") String identity, @JsonProperty("attributes") Map<String, KValue> attributes) {
        this.identity = identity;
        this.attributes = attributes;
    }

    String getIdentity() {
        return identity;
    }

    public KValue getValue(String attribute) {
        return attributes.get(attribute);
    }

    public KStringValue getStringValue(String attribute) {
        KValue value = attributes.get(attribute);

        if (value != null)
            return value.asString();

        return null;
    }

    public KBooleanValue getBooleanValue(String attribute) {
        KValue value = attributes.get(attribute);

        if (value != null)
            return value.asBoolean();

        return null;
    }

    public KNumberValue getNumberValue(String attribute) {
        KValue value = attributes.get(attribute);

        if (value != null)
            return value.asNumber();

        return null;
    }

    public static KUser create(String identity) {
        return new KUser(identity, new HashMap<String, KValue>() {{ }});
    }

    public static KUser create(String identity, Map<String, Object> attributes) {
        return new KUser(identity, new HashMap<String, KValue>() {{
            attributes.forEach((key, value) -> {
                if (value != null) put(key, KValue.create(value));
            });
        }});
    }

    public static KUser create(String identity, List<KUserAttribute> attributes) {
        return new KUser(identity, new HashMap<String, KValue>() {{
            attributes.forEach((it) -> {
                if (it.value != null) put(it.name, KValue.create(it.value));
            });
        }});
    }

    public static KUser anonymous() {
        return create("anonymous-${UUID.randomUUID().toString()}");
    }

//    // TODO remove only for test
//    public static KUser create(String identity, Attribute... attributes) {
//        return new KUser(identity, new HashMap<String, KValue>() {{
//            Arrays.stream(attributes).filter(Objects::nonNull).forEach(it -> put(it.name, KValue.create(it.value)));
//        }});
//    }

    public KUser with(KUserAttribute attribute) {
        if(attribute.value != null) this.attributes.put(attribute.name, KValue.create(attribute.value));
        return this;
    }

    public KUser with(String name, Object value) {
        if(value != null) this.attributes.put(name, KValue.create(value));
        return this;
    }
}
