//package com.fflags.client.feature;
//
//import com.fflags.client.FFClient;
//import com.fflags.client.exceptions.NotValidSdkKeyException;
//import org.junit.Test;
//
//import java.io.IOException;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
//public class FFlagsClientFeature {
//
//    @Test
//    public void should_throw_NotValidSdkKeyException_with_invalid_key() {
//        FFClient client = new FFClient("invalid_key");
//        try {
//            client.init();
//            fail("expected not valid SdkKey exception");
//        } catch (NotValidSdkKeyException e) {
//            assertEquals("Client initialized correctly", e.getMessage());
//        } catch (IOException e) {
//            fail("expected not valid SdkKey exception");
//        }
//    }
//}
