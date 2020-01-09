package com.fflags.client;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class FFlagsClientTest extends EasyMockSupport {

    @Test
    public void constructorThrowsExceptionWhenNullSdkKKey() {
        try(FFClient ffClient = new FFClient(null)) {
            fail("expected null pointer exception");
        } catch (NullPointerException | IOException e) {
            assertEquals("Sdk key must not be null", e.getMessage());
        }
    }
}
