package com.fflags.client;

import com.fflags.client.exceptions.NotValidSdkKeyException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FFHttpClient {

    public FFHttpClient() {
    }

    public void post(URL initURL) throws IOException {
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

        con.disconnect();
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
