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

import java.io.File;
import java.io.FileOutputStream;


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
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(AppConfig.getSiteURL());
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
