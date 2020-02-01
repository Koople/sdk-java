package com.fflags.client;

import com.fflags.client.exceptions.NotValidSdkKeyException;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


public class FFlagsClientTest extends EasyMockSupport {

    @Mock
    private FFClient client;

    @Test
    public void should_throws_exception_when_sdk_key_is_null() {
        try(FFClient ffClient = new FFClient(null)) {
            fail("expected null pointer exception");
        } catch (NullPointerException e) {
            assertEquals("Sdk key must not be null", e.getMessage());
        } catch (IOException e) {
            fail("expected null pointer exception");
        }
    }

    @Test
    public void should_return_a_validate_client_when_key_is_not_null() {
        try(FFClient ffClient = new FFClient("valid_key")) {
            assertNotNull(ffClient);
        } catch (NullPointerException | IOException e) {
            fail("expected new client");
        }
    }

    @Test
    public void should_throw_NotValidSdkKeyException_with_invalid_key() {
        FFClient client = new FFClient("invalid_key");
        try {
            client.init();
            fail("expected not valid SdkKey exception");
        } catch (NotValidSdkKeyException e) {
            assertEquals("Invalid SdkKey", e.getMessage());
        }
    }
}
