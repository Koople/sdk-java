package com.pataflags.sdk;

import com.pataflags.evaluator.PFEvaluation;
import com.pataflags.evaluator.PFUser;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(EasyMockRunner.class)
public class PFClientSpec extends EasyMockSupport {

    public static final String API_KEY = "b2096646-6e61-4d14-8180-544c5c10194a";

    @Test
    public void should_throws_exception_when_sdk_key_is_null() {
        try (PFClient pfClient = PFClient.initialize(null)) {
            fail("expected null pointer exception");
        } catch (NullPointerException e) {
            assertEquals("Sdk key must not be null", e.getMessage());
        } catch (IOException e) {
            fail("expected null pointer exception");
        }
    }

    @Test
    public void should_return_a_validate_client_when_key_is_not_null() {
        try (PFClient pfClient = PFClient.initialize(API_KEY)) {
            assertNotNull(pfClient);
        } catch (NullPointerException | IOException e) {
            fail("expected new client");
        }
    }

    @Test
    public void should_throw_IOException_when_init_with_invalid_key() {
        PFConfig config =  new PFConfig("invalid_key");
        try {
            PFClient.initialize("invalid_key");
            EasyMock.expectLastCall().andThrow(new IOException());
            fail("expected IOException");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains("Server returned HTTP response code: 500 for URL"));
        }
    }

    @Test
    public void should_evaluate() throws IOException {
        PFUser user = PFUser.create("test").attribute("username", "ldavid.gomez");
        PFClient client = PFClient.initialize(API_KEY);

        PFEvaluation evaluation = client.evaluate(user);
        assertEquals(evaluation.features, new HashMap<String, Boolean>() {{
            put("developer", true);
            put("developer-enabled", true);
            put("test-feature", false);
            put("developer-disabled", false);
        }});
    }

    @Test
    public void should_evaluate_is_enabled() throws IOException {
        PFUser user = PFUser.create("test").attribute("username", "ldavid.gomez");
        PFClient client = PFClient.initialize(API_KEY);

        Boolean isEnabled = client.isEnabled("developer", user);
        Assert.assertTrue(isEnabled);
    }

    @Test
    public void should_evaluate_is_not_enabled_for_unexisting_feature() throws IOException {
        PFUser user = PFUser.create("test").attribute("username", "ldavid.gomez");
        PFClient client = PFClient.initialize(API_KEY);

        Boolean isEnabled = client.isEnabled("non_valid_feature", user);
        Assert.assertFalse(isEnabled);
    }

    @Test
    public void should_evaluate_is_enabled_with_anonymous_user() throws IOException {
        PFClient client = PFClient.initialize(API_KEY);

        Boolean isEnabled = client.isEnabled("developer-enabled");
        Assert.assertTrue(isEnabled);
    }

    @Test
    public void should_evaluate_value_of_non_existing_config_with_anonymous_user_and_default_value() throws IOException {
        PFUser user = PFUser.anonymous();
        PFClient client = PFClient.initialize(API_KEY);

        String remoteConfig = client.valueOf("non-existing-remote-config", user, "default_value");
        Assert.assertEquals(remoteConfig,  "default_value");
    }

    @Test
    public void should_evaluate_value_of_non_existing_config_with_anonymous_user_and_no_default_value() throws IOException {
        PFUser user = PFUser.anonymous();
        PFClient client = PFClient.initialize(API_KEY);

        String remoteConfig = client.valueOf("non-existing-remote-config", user);
        Assert.assertEquals(remoteConfig,  "");
    }

    @Test
    public void should_evaluate_value_of_non_existing_config_without_user_and_default_value() throws IOException {
        PFClient client = PFClient.initialize(API_KEY);

        String remoteConfig = client.valueOf("non-existing-remote-config", "default_value");
        Assert.assertEquals(remoteConfig,  "default_value");
    }

    @Test
    public void should_evaluate_value_of_non_existing_config_without_user_neither_default_value() throws IOException {
        PFClient client = PFClient.initialize(API_KEY);

        String remoteConfig = client.valueOf("non-existing-remote-config");
        Assert.assertEquals(remoteConfig,  "");
    }

    @Test
    public void should_evaluate_value_of_with_anonymous_user_and_default_value() throws IOException {
        PFUser user = PFUser.anonymous();
        PFClient client = PFClient.initialize(API_KEY);

        String remoteConfig = client.valueOf("remote-config", user, "default_value");
        Assert.assertEquals(remoteConfig,  "production_default_value");
    }

    @Test
    public void should_evaluate_value_of_with_anonymous_user_and_no_default_value() throws IOException {
        PFUser user = PFUser.anonymous();
        PFClient client = PFClient.initialize(API_KEY);

        String remoteConfig = client.valueOf("remote-config", user);
        Assert.assertEquals(remoteConfig,  "production_default_value");
    }

    @Test
    public void should_evaluate_value_of_without_user_and_default_value() throws IOException {
        PFClient client = PFClient.initialize(API_KEY);

        String remoteConfig = client.valueOf("remote-config", "default_value");
        Assert.assertEquals(remoteConfig,  "production_default_value");
    }

    @Test
    public void should_evaluate_value_of_without_user_neither_default_value() throws IOException {
        PFClient client = PFClient.initialize(API_KEY);

        String remoteConfig = client.valueOf("remote-config");
        Assert.assertEquals(remoteConfig,  "production_default_value");
    }

    @Test
    public void should_evaluate_value_of_with_specific_user_and_no_default_value() throws IOException {
        PFUser user = PFUser.create("username").attribute("username", "ldavid.gomez");
        PFClient client = PFClient.initialize(API_KEY);

        String remoteConfig = client.valueOf("remote-config-for-user", user);
        Assert.assertEquals(remoteConfig,  "production_specific_value_for_specific_user");
    }

    @Test
    public void should_evaluate_value_of_with_specific_user_and_default_value() throws IOException {
        PFUser user = PFUser.create("username").attribute("username", "non_valid_user");
        PFClient client = PFClient.initialize(API_KEY);

        String remoteConfig = client.valueOf("remote-config-for-user", user);
        Assert.assertEquals(remoteConfig,  "production_default_value_for_user");
    }
}
