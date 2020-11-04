package com.pataflags.sdk;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PFHttpClient {

    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);

    public PFHttpClient() {
    }

    public ServerInitializeResponseDTO get(URL initURL, String apiKey) throws IOException {
        HttpURLConnection con = (HttpURLConnection) initURL.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(30000);
        con.setReadTimeout(30000);
        con.setRequestProperty("x-api-key", apiKey);

        try {
            con.getResponseCode();
        } catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        ServerInitializeResponseDTO dto = mapper.readValue(content.toString(), ServerInitializeResponseDTO.class);

        con.disconnect();

        return dto;
    }

//    private void checkResponseStatus(int status) {
//        System.out.println(status);
//        switch (status){
//            case 200:
//                System.out.println("Client initialized correctly");
//                break;
//            case 500:
//                throw new NotValidSdkKeyException();
//            default:
//                throw new RuntimeException();
//        }
//    }
}
