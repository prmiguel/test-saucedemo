package com.saucedemo.test;

import com.saucedemo.configs.AppConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    @Rule
    public TestName name = new TestName();

    // TODO: The following section can be moved to the gradle.build file
    @Rule
    public TestWatcher cleanImagesOnSuccess = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            String screenshotFolderPath = System.getProperty("screenshots.path");
            String screenshotPath = String.format("%s/%s.jpg",
                    screenshotFolderPath,
                    description.getMethodName());

            File image = new File(screenshotPath);

            if (image.exists())
                image.delete();
        }
    };

    protected WebDriver driver;

    @Before
    public void initializeDriver() {
        if (AppConfig.getBrowserName().equalsIgnoreCase("chrome"))
            initializeChrome();
        else if (AppConfig.getBrowserName().equalsIgnoreCase("firefox"))
            initializeFirefox();
        else
            throw new RuntimeException(String.format("Browser '%s' not supported.",
                    AppConfig.getBrowserName()));

        driver.get(AppConfig.getSiteURL());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // TODO: Move this logic to a different class
    private void initializeFirefox() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions firefoxOptions = new FirefoxOptions();

        if (AppConfig.getHeadlessMode().equalsIgnoreCase("true"))
            firefoxOptions.setHeadless(true);

        driver = new FirefoxDriver(firefoxOptions);
    }

    private void initializeChrome() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions chromeOptions = new ChromeOptions();

        if (AppConfig.getHeadlessMode().equalsIgnoreCase("true"))
            chromeOptions.setHeadless(true);

        driver = new ChromeDriver(chromeOptions);
    }

    @After
    public void closeDriver() {
        captureScreenshot(name.getMethodName());
        driver.quit();
    }

    public void captureScreenshot(String methodName) {
        String screenshotFolderPath = System.getProperty("screenshots.path");
        String screenshotPath = String.format("%s/%s.jpg", screenshotFolderPath, methodName);

        try {
            new File(screenshotFolderPath).mkdirs();

            FileOutputStream outPutFile = new FileOutputStream(screenshotPath);
            outPutFile.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            outPutFile.close();
        } catch (Exception e) {
            // TODO: Attach a default image
        }
    }
}
