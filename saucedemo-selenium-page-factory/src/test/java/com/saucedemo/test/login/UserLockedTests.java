package com.saucedemo.test.login;

import com.saucedemo.pages.LoginForm;
import com.saucedemo.test.BaseTest;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class UserLockedTests extends BaseTest {

    @Test
    public void errorNotificationDisplayed() {
        LoginForm loginForm = new LoginForm(driver);
        loginForm.loginIntoApp("locked_out_user", "secret_sauce");

        Assert.assertThat("the user should see the notification error:",
                loginForm.getNotificationError(),
                CoreMatchers.equalTo("Sorry, this user has been locked out."));
    }
}
