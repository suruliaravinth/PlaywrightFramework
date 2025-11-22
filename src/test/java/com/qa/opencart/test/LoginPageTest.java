package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void setupLoginPage() {
        loginPage = homePage.navigateToLoginPage();  // CLICK ONCE PER TEST
    }

    @Test(priority = 1)
    public void verifyLoginPageTitleTest() {
        String title = loginPage.getLoginPageTitle();
        //assert title.equals("Account Login") : "Login Page title does not match";
        Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE, "Login Page title does not match");
    }
    @Test(priority = 2)
    public void verifyForgotPwdLinkExistTest() {
        boolean flag = loginPage.isForgotPwdLinkExist();
        //assert flag : "Forgot Password link is not displayed";
        Assert.assertTrue(flag, "Forgot Password link is not displayed");
    }
    @Test(priority = 3)
    public void doLoginTest() {
        boolean isLoginSuccess = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
        //assert isLoginSuccess : "Login failed with valid credentials";
        Assert.assertTrue(isLoginSuccess, "Login failed with valid credentials");
    }
}
