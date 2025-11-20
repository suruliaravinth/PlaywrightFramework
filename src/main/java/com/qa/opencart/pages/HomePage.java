package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private Page page;
    private String searchBox = "input.form-control.input-lg[placeholder='Search']";
    private String searchIcon = "//button[@class='btn btn-default btn-lg']";
    private String searchPageHeader = "div[id='content'] h1";
    private String myAccountLink = "//span[@class='caret']";
    private String loginLink = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']";

    public HomePage(Page page) {
        this.page = page;
    }

    public String getHomePageTitle() {
        System.out.println("Home Page Title: " + page.title());
        return page.title();
    }

    public String getHomePageURL() {
        System.out.println("Home Page URL: " + page.url());
        return page.url();
    }

    public String doSearch(String productName) {
        page.locator(searchBox).fill(productName);
        page.locator(searchIcon).click();
        //page.fill(searchBox, productName);
        //page.click(searchIcon);
        System.out.println("Search Page Header: " + page.textContent(searchPageHeader));
        return page.textContent(searchPageHeader);
    }

    public LoginPage navigateToLoginPage() {
        page.locator(myAccountLink).click();
        page.locator(loginLink).click();
        return new LoginPage(page);
    }

}
