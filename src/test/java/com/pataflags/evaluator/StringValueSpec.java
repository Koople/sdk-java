package com.pataflags.evaluator;

import com.pataflags.evaluator.IntValue;
import com.pataflags.evaluator.StringValue;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringValueSpec extends EasyMockSupport {

    @Test
    public void string_equals() {
        assertTrue(new StringValue("a").equals(new StringValue("a")));
        assertFalse(new StringValue("a").equals(new StringValue("b")));
        assertFalse(new StringValue("1").equals(new IntValue(1)));
    }

    @Test
    public void string_contains() {
        assertTrue(new StringValue("abc").contains(new StringValue("a")));
        assertTrue(new StringValue("abc").contains(new StringValue("b")));
        assertTrue(new StringValue("abc").contains(new StringValue("c")));
        assertFalse(new StringValue("abc").contains(new StringValue("z")));
    }

    @Test
    public void string_greaterThan() {
        assertFalse(new StringValue("a").greaterThan(new StringValue("b")));
    }

    @Test
    public void string_greaterThanOrEquals() {
        assertFalse(new StringValue("a").greaterThanOrEquals(new StringValue("b")));
    }

    @Test
    public void string_lessThan() {
        assertFalse(new StringValue("a").lessThan(new StringValue("b")));
    }

    @Test
    public void string_lessThanOrEquals() {
        assertFalse(new StringValue("a").lessThanOrEquals(new StringValue("b")));
    }

}
