package com.saucedemo.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BaseTest {

    protected WebDriver driver;

    @Before
    public void downloadDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
    }

    @After
    public void close() {
        driver.quit();
    }

}
