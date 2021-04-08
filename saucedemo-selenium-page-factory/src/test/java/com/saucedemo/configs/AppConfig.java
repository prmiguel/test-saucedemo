package com.saucedemo.configs;

public class AppConfig {

    private static String SAUCEDEMO_URL = "saucedemo.web.url";

    private static String DEFAULT_SAUCEDEMO_URL = "https://www.saucedemo.com";

    public static String getSiteURL() {
        if (System.getProperties().containsKey(SAUCEDEMO_URL))
            return System.getProperty(SAUCEDEMO_URL);
        return DEFAULT_SAUCEDEMO_URL;
    }
}
