package com.saucedemo.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
    protected WebDriver wd;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private OrderHelper orderHelper;
    private String baseUrl;
    private String browserType = BrowserType.CHROME;
    private StringBuffer verificationErrors = new StringBuffer();

    public void init() {
        switch (browserType) {
            case "OPERA":
                OperaOptions oo = new OperaOptions();
                oo.addArguments("--headless");
                wd = new OperaDriver();
                break;
            case "FIREFOX":
                FirefoxOptions fo = new FirefoxOptions();
                fo.addArguments("--headless");
                wd = new FirefoxDriver();
                break;
            default:
                ChromeOptions co = new ChromeOptions();
                co.addArguments("--headless");
                wd = new ChromeDriver();
                break;
        }
        baseUrl = "https://www.google.com/";
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        orderHelper = new OrderHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("standard_user", "secret_sauce");
    }

    public void stop() {
        wd.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }


    public OrderHelper getOrderHelper() {
        return orderHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
