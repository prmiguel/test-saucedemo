package com.saucedemo.test.login;

import com.saucedemo.pages.LoginForm;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.test.BaseTest;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class LoginAcceptanceTests extends BaseTest {

    private final String username;
    private final String password;

    public LoginAcceptanceTests(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Parameterized.Parameters(name = "{index} with username={0} password={1}")
    public static Collection<Object[]> getCredentials() {
        return Arrays.asList(new Object[][]{
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
        });
    }

    @Test
    public void loginSuccessIntoApp() {
        LoginForm loginForm = new LoginForm(driver);
        loginForm.loginIntoApp(username, password);

        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertThat("the user should see the title:",
                productsPage.getPageTitle(),
                CoreMatchers.equalTo("PRODUCTS"));
    }
}
