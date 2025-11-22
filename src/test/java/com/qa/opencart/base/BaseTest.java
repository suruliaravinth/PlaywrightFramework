package com.qa.opencart.base;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.util.Properties;

public class BaseTest {
    PlaywrightFactory pf;
    Page page;
    protected Properties prop;
    protected HomePage homePage;
    protected LoginPage loginPage;

    @BeforeTest
    public void setup() throws FileNotFoundException {
        pf = new PlaywrightFactory();
        prop = pf.init_prop();
        page = pf.initializeBrowser(prop);
        homePage = new HomePage(page);
    }
    @AfterTest
    public void tearDown() {
        PlaywrightFactory.cleanUp();
    }
}