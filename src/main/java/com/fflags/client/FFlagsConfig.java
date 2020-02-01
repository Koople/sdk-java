package com.fflags.client;

import java.io.IOException;
import java.util.Properties;

public class FFlagsConfig {
    String propertiesFile;
    Properties configProps;

    public FFlagsConfig() {
        this.propertiesFile = "application.properties";
        this.configProps = new Properties();
        try {
            configProps.load(getClass().getClassLoader().getResourceAsStream(propertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
