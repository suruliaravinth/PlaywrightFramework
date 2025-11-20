package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void verifyLoginPageTitleTest() {
        String title = homePage.navigateToLoginPage().getLoginPageTitle();
        //assert title.equals("Account Login") : "Login Page title does not match";
        Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE, "Login Page title does not match");
    }
    @Test(priority = 2)
    public void verifyForgotPwdLinkExistTest() {
        boolean flag = homePage.navigateToLoginPage().isForgotPwdLinkExist();
        //assert flag : "Forgot Password link is not displayed";
        Assert.assertTrue(flag, "Forgot Password link is not displayed");
    }
    @Test(priority = 3)
    public void doLoginTest() {
        boolean isLoginSuccess = homePage.navigateToLoginPage().doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
        //assert isLoginSuccess : "Login failed with valid credentials";
        Assert.assertTrue(isLoginSuccess, "Login failed with valid credentials");
    }
}
