package io.koople.sdk;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

class KConfig {
    String apiKey;
    String propertiesFile = "application.properties";
    Properties configProps;

    private String protocol = "https";
    private String host = "sdk.koople.io";
    private int port = 443;
    private String initURL = "/proxy/server/initialize";

    KConfig(String apiKey) {
        this.apiKey = apiKey;
        configProps = new Properties();
        try {
            configProps.load(getClass().getClassLoader().getResourceAsStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    URL init() throws MalformedURLException {
        protocol = configProps.getProperty("koople.server.protocol") == null ? protocol : configProps.getProperty("koople.server.protocol");
        host = configProps.getProperty("koople.server.host") == null ? host : configProps.getProperty("koople.server.host");
        port = configProps.getProperty("koople.server.port") == null ? port : Integer.parseInt(configProps.getProperty("koople.server.port"));
        initURL = configProps.getProperty("koople.server.endpoint") == null ? initURL : configProps.getProperty("koople.server.endpoint");

        return new URL(protocol, host, port, initURL);
    }
}
