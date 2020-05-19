package com.pataflags.sdk;

import com.pataflags.sdk.exceptions.NotValidSdkKeyException;
import com.pataflags.sdk.values.IntValue;
import com.pataflags.sdk.values.StringValue;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(EasyMockRunner.class)
public class PFlagsClientSpec extends EasyMockSupport {

    User user = new User("pedro", new HashMap<String, Value<?>>() {{
        put("country", new StringValue("spain"));
        put("age", new IntValue(50));
    }});

    private List<Feature> features = new ArrayList<Feature>() {{
        add(new Feature("enabledForAll", FeatureTargeting.all));
    }};

    @Test
    public void should_throws_exception_when_sdk_key_is_null() {
        try (PFClient PFClient = new PFClient(null)) {
            fail("expected null pointer exception");
        } catch (NullPointerException e) {
            assertEquals("Sdk key must not be null", e.getMessage());
        } catch (IOException e) {
            fail("expected null pointer exception");
        }
    }

    @Test
    public void should_return_a_validate_client_when_key_is_not_null() {
        try (PFClient PFClient = new PFClient("valid_key")) {
            assertNotNull(PFClient);
        } catch (NullPointerException | IOException e) {
            fail("expected new client");
        }
    }

    @Test
    public void should_throw_NotValidSdkKeyException_when_init_with_invalid_key() {
        PFClient client = new PFClient("invalid_key");
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
        PFClient client = partialMockBuilder(PFClient.class)
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

    @Mock
    private FFHttpClient mockHttpClient;

    @Test
    public void should_evaluate() throws IOException {
        FFlagsConfig config = new FFlagsConfig("apiKey");
        EasyMock.expect(mockHttpClient.post(config.init())).andReturn(features);
        EasyMock.replay(mockHttpClient);

        PFClient client = new PFClient("apiKey", mockHttpClient);
        client.init();
        Map<String, Boolean> evaluation = client.evaluate(user);

        assertEquals(evaluation, new HashMap<String, Boolean>() {{ put("enabledForAll", true); }});
    }
}
