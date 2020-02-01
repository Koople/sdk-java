package com.fflags.client;

import org.easymock.EasyMockSupport;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


public class FFlagsClientTest extends EasyMockSupport {

    @Test
    public void should_throws_exception_when_sdk_key_is_null() {
        try(FFClient ffClient = new FFClient(null)) {
            fail("expected null pointer exception");
        } catch (NullPointerException | IOException | FFClient.NotValidSdkKeyException e) {
            assertEquals("Sdk key must not be null", e.getMessage());
        }
    }

//    @Test
//    public void should_return_config_object_with_filled_properties() {
//        FFlagsConfig config = new FFlagsConfig();
//        String serverURL = config.appProps.getProperty("fflags.server.url");
//        String serverPort = config.appProps.getProperty("fflags.server.port");
//
//        assertNotNull(serverURL);
//        assertNotNull(serverPort);
//    }

    @Test
    public void should_return_a_validate_client_when_key_is_correct() {
        try(FFClient ffClient = new FFClient("validKey")) {
            assertNotNull(ffClient);
        } catch (NullPointerException | IOException | FFClient.NotValidSdkKeyException e) {
            fail("expected new client");
        }
    }
}
