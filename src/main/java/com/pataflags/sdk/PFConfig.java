package com.pataflags.sdk;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

class PFConfig {
    String sdkKey;
    String propertiesFile;
    Properties configProps;

    PFConfig(String sdkKey) {
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
        String protocol = this.configProps.getProperty("fflags.server.protocol");
        String host = this.configProps.getProperty("fflags.server.host");
        int port = Integer.parseInt(this.configProps.getProperty("fflags.server.port"));
        String initURL = this.configProps.getProperty("fflags.server.endpoint");

        return new URL(protocol, host, port, initURL);
    }
}
