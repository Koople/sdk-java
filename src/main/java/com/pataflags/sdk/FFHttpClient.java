package com.pataflags.sdk;

import com.pataflags.evaluator.Feature;
import com.pataflags.sdk.exceptions.NotValidSdkKeyException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FFHttpClient {

//    val mapper: ObjectMapper = jacksonObjectMapper()
//        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//        .registerModule(JavaTimeModule())
//            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)

    public FFHttpClient() {
    }

    public List<Feature> post(URL initURL) throws IOException {
        HttpURLConnection con = (HttpURLConnection) initURL.openConnection();
        con.setRequestMethod("POST");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        checkResponseStatus(con.getResponseCode());
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

//        mapper.readValue(json)

        con.disconnect();

        return new ArrayList<>();
    }

    private void checkResponseStatus(int status) {
        System.out.println(status);
        switch (status){
            case 200:
                System.out.println("Client initialized correctly");
                break;
            case 500:
                throw new NotValidSdkKeyException();
            default: throw new RuntimeException();
        }
    }
}
