package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginForm {

    private final WebDriver driver;

    @FindBy(css = "#user-name")
    private WebElement usernameInput;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    @FindBy(css = "button.error-button")
    private WebElement errorCloseButton;

    @FindBy(css = "#login-button")
    private WebElement loginButton;

    public LoginForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterTheUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void enterThePassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickOnLogin() {
        loginButton.click();
    }

    public void loginIntoApp(String username, String password) {
        enterTheUsername(username);
        enterThePassword(password);
        clickOnLogin();
    }

    public void closeNotificationError() {
        errorCloseButton.click();
    }

    public String getNotificationError() {
        return errorMessage.getText();
    }
}
