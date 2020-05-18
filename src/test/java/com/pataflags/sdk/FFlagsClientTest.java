package com.pataflags.sdk;

import com.pataflags.sdk.exceptions.NotValidSdkKeyException;
import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FFlagsClientTest extends EasyMockSupport {

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
    public void should_throw_NotValidSdkKeyException_when_init_with_invalid_key() {
            FFClient client = new FFClient("invalid_key");
        try {
            client.init();
            EasyMock.expectLastCall().andThrow(new NotValidSdkKeyException());
            fail("expected NotValidSdkKeyException");
        } catch (NotValidSdkKeyException e) {
            assertEquals("Invalid SdkKey", e.getMessage());
        }
    }

    @Test
    public void should_return_valid_result_when_init_with_valid_key() {
        FFClient client = partialMockBuilder(FFClient.class)
                .withConstructor("valid_key")
                .addMockedMethod("init")
                .createMock();

        try {
            client.init();
            assertNotNull(client);
        } catch (NotValidSdkKeyException e) {
            fail("exception non expected");
        }
    }
}
