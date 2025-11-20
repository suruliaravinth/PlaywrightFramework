package com.qa.opencart.test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    @Test(priority = 1)
    public void verifyHomePageTitleTest() {
        String title = homePage.getHomePageTitle();
        //assert title.equals("Your Store") : "Home Page title does not match";
        Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE, "Home Page title does not match");
    }

    @Test(priority = 2)
    public void verifyHomePageURLTest() {
        String url = homePage.getHomePageURL();
        //assert url.contains(prop.getProperty("url").trim()) : "Home Page URL does not match";
        Assert.assertTrue(url.contains(prop.getProperty("url").trim()), "Home Page URL does not match");
    }

    @DataProvider
    public Object[][] getProductData() {
        return new Object[][] {
                {"Macbook"}, {"iPhone"},    {"Samsung"}
        };
    }
    @Test(dataProvider = "getProductData",priority = 3)
    public void verifySearchFunctionalityTest(String productName) {
        //String productName = "Macbook";
        String headerText = homePage.doSearch(productName);
        //assert headerText.contains(productName) : "Search results do not match the product name";
        Assert.assertEquals(headerText, "Search - "+productName, "Search results do not match the product name");
        Assert.assertTrue(headerText.contains(productName), "Search results do not match the product name");
    }
}
