package com.qa.opencart.factory;

import com.microsoft.playwright.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

public class PlaywrightFactory {

    //Playwright playwright;
    //Browser browser;
    //BrowserContext browserContext;
    //Page page;
    Properties prop;

    private static ThreadLocal<Playwright>tlPlaywright=new ThreadLocal<>();
    private static ThreadLocal<Browser>tlBrowser=new ThreadLocal<>();
    private static ThreadLocal<BrowserContext>tlBrowserContext=new ThreadLocal<>();
    private static ThreadLocal<Page>tlPage=new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }

    public Page initializeBrowser(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is : " + browserName);
        //playwright = Playwright.create();
        tlPlaywright.set(Playwright.create());
        switch (browserName.toLowerCase()){
            case "chrome":
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome").setSlowMo(5000).setArgs(List.of("--start-maximized")));
                tlBrowser.set(tlPlaywright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome").setSlowMo(5000).setArgs(List.of("--start-maximized"))));
                break;
            case "edge":
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge").setSlowMo(5000).setArgs(List.of("--start-maximized")));
                tlBrowser.set(tlPlaywright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge").setSlowMo(5000).setArgs(List.of("--start-maximized"))));
                break;
            case "chromium":
                //browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000).setArgs(List.of("--start-maximized")));
                tlBrowser.set(tlPlaywright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000).setArgs(List.of("--start-maximized"))));
                break;
            case "firefox":
                //browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000).setArgs(List.of("--start-maximized")));
                tlBrowser.set(tlPlaywright.get().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000).setArgs(List.of("--start-maximized"))));
                break;
            case "safari":
                //browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000).setArgs(List.of("--start-maximized")));
                tlBrowser.set(tlPlaywright.get().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000).setArgs(List.of("--start-maximized"))));
                break;
            default:
                System.out.println("Please pass the correct browser name : " + browserName);
                break;
        }
        //browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null).setRecordVideoSize(1920,1080).setRecordVideoDir(Paths.get("test-output/videos")));
        tlBrowserContext.set(tlBrowser.get().newContext(new Browser.NewContextOptions().setViewportSize(null).setRecordVideoSize(1920,1080).setRecordVideoDir(Paths.get("test-output/videos"))));
        //page=browserContext.newPage();
        tlPage.set(tlBrowserContext.get().newPage());
        //page.navigate(prop.getProperty("url").trim());
        getPage().navigate(prop.getProperty("url").trim());
        return getPage();
    }
    public Properties init_prop() throws FileNotFoundException {
        // Method to initialize properties can be added here
        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        //getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);
        //return base64Path;
        return Base64.getEncoder().encodeToString(buffer);
    }
}
