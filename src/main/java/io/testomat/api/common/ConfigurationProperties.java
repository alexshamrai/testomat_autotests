package io.testomat.api.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationProperties {

    private static final Properties properties = new Properties();
    public static final String RESOURCE_NAME = "configuration.properties";

    static {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream(RESOURCE_NAME)) {
            properties.load(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

