package com.saucedemo.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
    protected WebDriver wd;
    private NavigationHelper navigationHelper;
    private OrderHelper orderHelper;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        wd = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        orderHelper = new OrderHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        login("standard_user", "secret_sauce");
    }

    private void login(String username, String password) {
        wd.get("https://www.saucedemo.com/");
        wd.findElement(By.id("user-name")).click();
        wd.findElement(By.id("user-name")).clear();
        wd.findElement(By.id("user-name")).sendKeys(username);
        wd.findElement(By.id("password")).click();
        wd.findElement(By.id("password")).clear();
        wd.findElement(By.id("password")).sendKeys(password);
        wd.findElement(By.id("login-button")).click();
    }

    public void logout() {
        wd.findElement(By.id("react-burger-menu-btn")).click();
        wd.findElement(By.id("logout_sidebar_link")).click();
    }

    public void stop() {
        wd.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = wd.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    public OrderHelper getOrderHelper() {
        return orderHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
