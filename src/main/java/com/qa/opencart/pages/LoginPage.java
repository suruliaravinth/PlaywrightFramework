package com.qa.opencart.pages;


import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;
    private String emailIdFd = "//input[@id='input-email']";
    private String passwordFld = "//input[@id='input-password']";
    private String loginBtn = "//input[@value='Login']";
    private String forgotPwdLink = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
    private String logoutLink = "//a[@class='list-group-item'][normalize-space()='Logout']";

    public LoginPage(Page page) {
        this.page = page;
    }
    public String getLoginPageTitle() {
        System.out.println("Login Page Title: " + page.title());
        return page.title();
    }
    public boolean isForgotPwdLinkExist() {
        System.out.println("Forgot Password Link is displayed: " + page.locator(forgotPwdLink).isVisible());
        //return page.isVisible(forgotPwdLink);
        return page.locator(forgotPwdLink).isVisible();
    }
    public boolean doLogin(String username, String password) {
        System.out.println("Login with username: " + username + " and password: " + password);
        page.locator(emailIdFd).fill(username);
        page.locator(passwordFld).fill(password);
        page.locator(loginBtn).click();
        if (page.isVisible(logoutLink)){
            System.out.println("Login Successful");
            return true;
        }
        return false;
    }
}
