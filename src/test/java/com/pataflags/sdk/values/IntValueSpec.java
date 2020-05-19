package com.pataflags.sdk.values;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntValueSpec extends EasyMockSupport {

    @Test
    public void int_equals() {
        assertTrue(new IntValue(1).equals(new IntValue(1)));
        assertFalse(new IntValue(1).equals(new IntValue(2)));
        assertFalse(new IntValue(1).equals(new StringValue("1")));
    }

    @Test
    public void int_contains() {
        assertFalse(new IntValue(1).contains(new IntValue(1)));
    }

    @Test
    public void int_greaterThan() {
        assertTrue(new IntValue(2).greaterThan(new IntValue(1)));
        assertFalse(new IntValue(1).greaterThan(new IntValue(1)));
        assertFalse(new IntValue(2).greaterThan(new StringValue("1")));
    }

    @Test
    public void int_greaterThanOrEquals() {
        assertTrue(new IntValue(2).greaterThanOrEquals(new IntValue(1)));
        assertTrue(new IntValue(2).greaterThanOrEquals(new IntValue(2)));
        assertFalse(new IntValue(1).greaterThanOrEquals(new IntValue(2)));
        assertFalse(new IntValue(2).greaterThanOrEquals(new StringValue("1")));
    }

    @Test
    public void int_lessThan() {
        assertTrue(new IntValue(1).lessThan(new IntValue(2)));
        assertFalse(new IntValue(1).lessThan(new IntValue(1)));
        assertFalse(new IntValue(1).lessThan(new StringValue("2")));
    }

    @Test
    public void int_lessThanOrEquals() {
        assertTrue(new IntValue(1).lessThanOrEquals(new IntValue(2)));
        assertTrue(new IntValue(2).lessThanOrEquals(new IntValue(2)));
        assertFalse(new IntValue(2).lessThanOrEquals(new IntValue(1)));
        assertFalse(new IntValue(1).lessThanOrEquals(new StringValue("2")));
    }

}
