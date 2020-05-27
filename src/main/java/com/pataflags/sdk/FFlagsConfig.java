package com.pataflags.sdk;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

class FFlagsConfig {
    String sdkKey;
    String propertiesFile;
    Properties configProps;

    FFlagsConfig(String sdkKey) {
        this.sdkKey = sdkKey;
        this.propertiesFile = "application.properties";
        this.configProps = new Properties();
        try {
            this.configProps.load(getClass().getClassLoader().getResourceAsStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    URL init() throws MalformedURLException {
        String protocol = "http";
        String host = "localhost";
        int port = 8081;
        String initURL = "/proxy/server/initialize";

        return new URL(protocol, host, port, initURL);
    }
}
