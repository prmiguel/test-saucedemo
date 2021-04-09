package com.saucedemo.test.login;

import com.saucedemo.pages.LoginForm;
import com.saucedemo.test.BaseTest;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class UserCanNotLoginTests extends BaseTest {

    private final String username;
    private final String password;

    public UserCanNotLoginTests(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Parameterized.Parameters(name = "{index} with username={0} password={1}")
    public static Collection<Object[]> getCredentials() {
        return Arrays.asList(new Object[][]{
                {"standard_user", "wrong_password"},
                {"no_valid_user", "secret_sauce"},
                {"STANDARD_USER", "secret_sauce"},
                {"standard_user", "SECRET_SAUCE"},
        });
    }

    @Test
    public void errorNotificationDisplayed() {
        LoginForm loginForm = new LoginForm(driver);
        loginForm.loginIntoApp(username, password);

        Assert.assertThat("the user should see the notification error:",
                loginForm.getNotificationError(),
                CoreMatchers.containsString("Username and password do not match any user in this service"));
    }
}
