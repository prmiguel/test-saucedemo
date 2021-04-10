package com.saucedemo.configs;

public class AppConfig {

    private static final String SAUCEDEMO_URL = "saucedemo.web.url";
    private static final String WEB_BROWSER_NAME = "browser.name";
    private static final String WEB_BROWSER_HEADLESS = "browser.headless";

    private static final String DEFAULT_SAUCEDEMO_URL = "https://www.saucedemo.com";
    private static final String DEFAULT_WEB_BROWSER_NAME = "chrome";
    private static final String DEFAULT_WEB_BROWSER_HEADLESS = "true";

    public static String getSiteURL() {
        if (System.getProperty(SAUCEDEMO_URL) != null)
            if (!System.getProperty(SAUCEDEMO_URL).equals(""))
                return System.getProperty(SAUCEDEMO_URL);

        return DEFAULT_SAUCEDEMO_URL;
    }

    public static String getBrowserName() {
        if (System.getProperty(WEB_BROWSER_NAME) != null)
            if (!System.getProperty(WEB_BROWSER_NAME).equals(""))
                return System.getProperty(WEB_BROWSER_NAME);

        return DEFAULT_WEB_BROWSER_NAME;
    }

    public static String getHeadlessMode() {
        if (System.getProperty(WEB_BROWSER_HEADLESS) != null)
            if (!System.getProperty(WEB_BROWSER_HEADLESS).equals(""))
                return System.getProperty(WEB_BROWSER_HEADLESS);
        return DEFAULT_WEB_BROWSER_HEADLESS;
    }
}
