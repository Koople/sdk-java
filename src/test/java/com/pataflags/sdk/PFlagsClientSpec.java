package com.pataflags.sdk;

import com.pataflags.evaluator.PFEvaluation;
import com.pataflags.evaluator.PFUser;
import com.pataflags.sdk.exceptions.NotValidSdkKeyException;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(EasyMockRunner.class)
public class PFlagsClientSpec extends EasyMockSupport {

//    Segment spainAdults = new Segment(new ArrayList<Rule>() {{
//        add(new Rule(0, new ArrayList<Statement>(){{
//            add(new Statement.ANY("country", Operator.EQUALS, new ArrayList<Value>() {{
//                add(new StringValue("spain"));
//            }}));
//            add(new Statement.NUMBER("age", Operator.GREATER_THAN_OR_EQUALS, new ArrayList<NumberValue>() {{
//                add(new NumberValue(18));
//            }}));
//        }}));
//    }});
//
//    Segment eeuuAdults = new Segment(new ArrayList<Rule>() {{
//        add(new Rule(0, new ArrayList<Statement>(){{
//            add(new Statement.ANY("country", Operator.EQUALS, new ArrayList<Value>() {{
//                add(new StringValue("eeuu"));
//            }}));
//            add(new Statement.NUMBER("age", Operator.GREATER_THAN_OR_EQUALS, new ArrayList<NumberValue>() {{
//                add(new NumberValue(21));
//            }}));
//        }}));
//    }});
//
//    User user = new User("pedrito", new HashMap<String, Value>() {{
//        put("country", new StringValue("spain"));
//        put("age", new NumberValue(18));
//    }});
//
//    private List<Feature> features = new ArrayList<Feature>() {{
//        add(new Feature("disabledForAll", FeatureTargeting.DISABLED_FOR_ALL));
//        add(new Feature("enabledForHimself", FeatureTargeting.ENABLED_FOR_SOME_USERS, new ArrayList<String>() {{ add("pedrito"); }}, new ArrayList<Segment>()));
//        add(new Feature("enabledForOther", FeatureTargeting.ENABLED_FOR_SOME_USERS, new ArrayList<String>() {{ add("paco"); }}, new ArrayList<Segment>()));
//        add(new Feature("enabledForSpainAdults", FeatureTargeting.ENABLED_FOR_SOME_USERS, new ArrayList<String>(), new ArrayList<Segment>() {{ add(spainAdults); }}));
//        add(new Feature("enabledForEeuuAdults", FeatureTargeting.ENABLED_FOR_SOME_USERS, new ArrayList<String>(), new ArrayList<Segment>() {{ add(eeuuAdults); }}));
//        add(new Feature("enabledForAll", FeatureTargeting.ENABLED_FOR_ALL));
//    }};

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
    public void should_throw_NotValidSdkKeyException_when_init_with_invalid_key() throws IOException {
        FFlagsConfig config =  new FFlagsConfig("invalid_key");
        FFHttpClient httpClient = EasyMock.createMock(FFHttpClient.class);

        EasyMock.expect(httpClient.get(config.init(), "invalid_key")).andThrow(new NotValidSdkKeyException());

        EasyMock.replay(httpClient);
        PFClient client = new PFClient("invalid_key", httpClient);

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

    // TODO It is repeat into EvaluatorSpec, is a sample mocks
//    @Test
//    public void should_evaluate() throws IOException {
//        FFlagsConfig config = new FFlagsConfig("apiKey");
//        EasyMock.expect(mockHttpClient.post(config.init())).andReturn(features);
//        EasyMock.replay(mockHttpClient);
//
//        PFClient client = new PFClient("apiKey", mockHttpClient);
//        client.init();
//        Evaluation evaluation = client.evaluate(user);
//
//        assertEquals(evaluation.evaluatedFeatures, new HashMap<String, Boolean>() {{
//            put("disabledForAll", false);
//            put("enabledForHimself", true);
//            put("enabledForOther", false);
//            put("enabledForSpainAdults", true);
//            put("enabledForEeuuAdults", false);
//            put("enabledForAll", true);
//        }});
//    }

    @Test
    public void should_evaluate() throws IOException {
        PFUser user = PFUser.create("test", new PFUser.Attribute("username", "davsertor"));
        PFClient client = new PFClient("4e1f34d7-f45c-4642-90a4-e84d84f55242", new FFHttpClient());

        client.init();

        PFEvaluation evaluation = client.evaluate(user);
        //{remote-config=true, documentation=false, landing-page-awesome-feature=false, metrics=false}
        assertEquals(evaluation.features, new HashMap<String, Boolean>() {{
            put("disabled-for-all", false);
            put("enabled-for-himself", true);
            put("enabled-for-adults", true);
            put("enabled-for-all", true);
        }});
    }
}
